from pydantic import BaseModel, field_validator, model_validator
from datetime import datetime


class AnalysisYears(BaseModel):
    start_year: int
    end_year: int

    @field_validator("start_year")
    @classmethod
    def validate_start_year(cls, v):
        if v < 2020:
            raise ValueError("start_year must be greater than or equal to 2020.")
        return v

    @field_validator("end_year")
    @classmethod
    def validate_end_year(cls, v):
        current_year = datetime.now().year
        if v >= current_year:
            raise ValueError("end_year must be less than the current year.")
        return v

    @model_validator(mode="after")
    def check_year_relation(self):
        if self.end_year <= self.start_year:
            raise ValueError("end_year must be greater than start_year.")
        return self
