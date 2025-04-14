from pydantic import BaseModel, model_validator, Field
from typing import List, Optional, Literal, Union


# --- GeoJSON Polygon ---
class PolygonGeometry(BaseModel):
    type: Literal["Polygon"]
    coordinates: List[List[List[float]]]  # List of rings


# --- GeoJSON MultiPolygon ---
class MultiPolygonGeometry(BaseModel):
    type: Literal["MultiPolygon"]
    coordinates: List[List[List[List[float]]]]  # List of polygons


# --- Custom: Point + Radius (Buffer Circle) ---
class CircleGeometry(BaseModel):
    type: Literal["Point"]
    coordinates: List[float]  # [lng, lat]
    radius: float  # in meters


# --- Custom: BBox (non-GeoJSON but common) ---
class BBoxGeometry(BaseModel):
    bbox: List[float] = Field(
        ..., min_items=4, max_items=4
    )  # [minLng, minLat, maxLng, maxLat]


# --- Unified input model with validation ---
class AreaInput(BaseModel):
    polygon: Optional[PolygonGeometry] = None
    multipolygon: Optional[MultiPolygonGeometry] = None
    circle: Optional[CircleGeometry] = None
    bbox: Optional[BBoxGeometry] = None

    @model_validator(mode="after")
    def validate_exactly_one(self):
        fields = [self.polygon, self.multipolygon, self.circle, self.bbox]
        provided = [f for f in fields if f is not None]
        if len(provided) != 1:
            raise ValueError(
                f"Exactly one of polygon, multipolygon, circle, or bbox must be provided."
            )
        return self
