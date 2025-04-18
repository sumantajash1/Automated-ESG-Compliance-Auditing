from fastapi import APIRouter
from app.services.prediction_service import PredictionService
from app.utils.data_preprocessor import preprocess_input
from app.validators.input_schema import InputData
import pandas as pd

api_bp = APIRouter()

@api_bp.post("/predict")
def predict(input_data: InputData):
    try:
        # Convert input data to DataFrame
        df = pd.DataFrame([record.dict() for record in input_data.data])
        
        # Preprocess the input data
        processed_data = preprocess_input(df, PredictionService.get_model_columns())
        
        # Make predictions
        model = PredictionService.get_model()
        predictions = model.predict(processed_data).tolist()

        return {"predictions": predictions}
    
    except Exception as e:
        return {"error": str(e)}
