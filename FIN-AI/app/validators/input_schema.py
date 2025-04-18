from pydantic import BaseModel
from typing import List

class BusinessRecord(BaseModel):
    sales: float
    revenue: float
    expenses: float
    profit: float
    tax_paid: float

class InputData(BaseModel):
    data: List[BusinessRecord]
