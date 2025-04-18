import joblib
from app.config import Config

class PredictionService:
    _model = None
    _model_columns = None

    @classmethod
    def get_model(cls):
        if cls._model is None:
            cls._model = joblib.load(Config.MODEL_PATH)
        return cls._model

    @classmethod
    def get_model_columns(cls):
        if cls._model_columns is None:
            cls._model_columns = joblib.load(Config.MODEL_COLUMNS_PATH)
        return cls._model_columns
