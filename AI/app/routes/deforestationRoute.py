# app/routes/deforestation.py

import json
from fastapi import APIRouter, HTTPException, BackgroundTasks, Depends
import requests
from fastapi.responses import JSONResponse
from app.validators.geometrySchema import AreaInput
from app.services.deforestationService import compute_deforestation
from pydantic import HttpUrl
from app.validators.queryYearSchema import AnalysisYears
from app.auth.verify_api_key import verify_api_key

router = APIRouter()


def deforestation_background_task(
    area: AreaInput,
    callback_url: HttpUrl,
    start_year: int,
    end_year: int,
    supplier_id: str,
    location_id: str,
):
    """
    Runs the deforestation computation in the background.
    This is called asynchronously and will not block the FastAPI event loop.
    """
    result = compute_deforestation(area, start_year, end_year)

    fullCallbackUrl = (
        f"{callback_url}?supplierId={supplier_id}&locationId={location_id}"
    )
    print("Callback URL:", fullCallbackUrl)
    # headers = {"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJHU1Q1MDAwMDAwMDAiLCJyb2xlIjoiU1VQUExJRVIiLCJpYXQiOjE3NDUwNDEzMDgsImV4cCI6MTc0NTA3NzMwOH0.3WloFyOUohmGv7hLSa--MNBZ3RR8K06pc8FuC_n76OCU-Kv6N3WZmF6sgHOFVxI2CoE4zs-b_xHnzFsOTBudHA"}
    response = requests.post(
        fullCallbackUrl,
        json=result,
        # headers=headers
    )
    print("I send the data to the callback URL")
    print("RESPONSE", response)
    return response


@router.post("/deforestation/check")
async def deforestation_handler(
    area: AreaInput,
    callback_url: HttpUrl,
    background_tasks: BackgroundTasks,
    years: AnalysisYears = Depends(),
    supplier_id: str = None,
    location_id: str = None,
    auth: bool = Depends(verify_api_key),
):
    try:
        background_tasks.add_task(
            deforestation_background_task,
            area,
            callback_url,
            years.start_year,
            years.end_year,
            supplier_id,
            location_id,
        )
        return {
            "message": "Deforestation computation started. You will get result at the callback URL."
        }
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
