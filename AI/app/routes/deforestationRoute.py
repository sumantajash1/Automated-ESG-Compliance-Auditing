# app/routes/deforestation.py

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
    area: AreaInput, callback_url: HttpUrl, start_year: int, end_year: int
):
    """
    Runs the deforestation computation in the background.
    This is called asynchronously and will not block the FastAPI event loop.
    """
    result = compute_deforestation(area, start_year, end_year)
    response = requests.post(callback_url, json=result)
    return response


@router.post("/deforestation/check")
async def deforestation_handler(
    area: AreaInput,
    callback_url: HttpUrl,
    background_tasks: BackgroundTasks,
    years: AnalysisYears = Depends(),
    auth: bool = Depends(verify_api_key),
):
    try:
        background_tasks.add_task(
            deforestation_background_task,
            area,
            callback_url,
            years.start_year,
            years.end_year,
        )
        return {
            "message": "Deforestation computation started. You will get result at the callback URL."
        }
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
