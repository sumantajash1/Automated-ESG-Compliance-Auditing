import ee
from fastapi import HTTPException
from app.validators.geometrySchema import AreaInput


def convert_to_ee_geometry(input: AreaInput) -> ee.Geometry:
    if input.polygon:
        return ee.Geometry.Polygon(input.polygon.coordinates)

    elif input.multipolygon:
        return ee.Geometry.MultiPolygon(input.multipolygon.coordinates)

    elif input.circle:
        lng, lat = input.circle.coordinates
        point = ee.Geometry.Point([lng, lat])
        return point.buffer(input.circle.radius)

    elif input.bbox:
        [min_lng, min_lat, max_lng, max_lat] = input.bbox.bbox
        return ee.Geometry.Rectangle([min_lng, min_lat, max_lng, max_lat])

    else:
        raise HTTPException(status_code=400, detail="Invalid geometry input")
