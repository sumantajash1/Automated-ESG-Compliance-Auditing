# app/services/deforestation.py

import ee
from app.validators.geometrySchema import AreaInput
from app.utils.geeHelper import convert_to_ee_geometry
from app.constants import (
    THRESHOLD_FOR_DIFORESTATION,
    THRESHOLD_FOR_SELECTING_FOREST,
    CLOUD_MASK_THRESHOLD,
)

#Make sure Earth Engine is initialized
try:
    ee.Authenticate()
    ee.Initialize(project="ee-dogrig507")
except Exception as e:
    ee.Authenticate()
    ee.Initialize()
    print("Earth Engine is already initialized or not available:", e)


def compute_deforestation(input: AreaInput, start_year, end_year) -> float:
    """
    Computes deforestation loss (in hectares) from GEE using Hansen dataset.
    Accepts validated AreaInput which can be polygon, multipolygon, circle, or bbox.
    """
    # Convert to Earth Engine geometry
    geom = convert_to_ee_geometry(input)

    total_area_m2 = geom.area().getInfo()  # Get the area in square meters

    result = []

    for year in range(start_year, end_year):
        startDatePrev = f"{year}-10-01"
        endDatePrev = f"{year}-12-31"
        startDateAft = f"{year + 1}-10-01"
        endDateAft = f"{year + 1}-12-31"

        collectionPrev = (
            ee.ImageCollection("COPERNICUS/S2_SR_HARMONIZED")
            .filterBounds(geom)
            .filterDate(startDatePrev, endDatePrev)
            .filter(ee.Filter.lt("CLOUDY_PIXEL_PERCENTAGE", CLOUD_MASK_THRESHOLD))
        )

        collectionAft = (
            ee.ImageCollection("COPERNICUS/S2_SR_HARMONIZED")
            .filterBounds(geom)
            .filterDate(startDateAft, endDateAft)
            .filter(ee.Filter.lt("CLOUDY_PIXEL_PERCENTAGE", CLOUD_MASK_THRESHOLD))
        )

        # calculate image with median value
        imagePrev = collectionPrev.median()
        imageAft = collectionAft.median()

        # Calculate NDVI for both images
        ndviPrev = imagePrev.normalizedDifference(["B8", "B4"]).rename("NDVI")
        ndviAft = imageAft.normalizedDifference(["B8", "B4"]).rename("NDVI")

        # Calculate NDVI difference
        ndviDiff = ndviAft.subtract(ndviPrev).rename("NDVI_Diff")

        deforestation_mask = ndviDiff.lt(THRESHOLD_FOR_DIFORESTATION)

        pixel_area = ee.Image.pixelArea().clip(geom)
        loss_area_image = deforestation_mask.multiply(pixel_area).rename("Loss_Area")

        area_stats = loss_area_image.reduceRegion(
            reducer=ee.Reducer.sum(), geometry=geom, scale=10, maxPixels=1e13
        )

        area_lost_m2 = area_stats.get("Loss_Area")

        initial_forest_mask = ndviPrev.gt(THRESHOLD_FOR_SELECTING_FOREST)
        initial_area_image = initial_forest_mask.multiply(pixel_area).rename(
            "Initial_Area"
        )

        initial_area_stats = initial_area_image.reduceRegion(
            reducer=ee.Reducer.sum(), geometry=geom, scale=10, maxPixels=1e13
        )

        initial_forest_area_m2 = initial_area_stats.get("Initial_Area")

        percentage_loss = ee.Algorithms.If(
            ee.Number(initial_forest_area_m2).gt(0),
            ee.Number(area_lost_m2)
            .divide(ee.Number(initial_forest_area_m2))
            .multiply(100),
            0,
        )

        deforestation_detected = ee.Algorithms.IsEqual(ee.Number(area_lost_m2).gt(0), 1)

        result.append(
            ee.Dictionary(
                {
                    "deforestation_detected": deforestation_detected,
                    "percentage_loss": percentage_loss,
                    "Initial_forest_area_m2": initial_forest_area_m2,
                    "area_lost_meter_square": area_lost_m2,
                    "startYear": f"{year}",
                    "endYear": f"{year + 1}",
                }
            )
        )

    return {
        "total_area_m2": total_area_m2,
        "deforestation_results": ee.List(result).getInfo(),
    }
