import os

class Config:
    # SECRET_KEY = os.environ.get('SECRET_KEY') or 'your-secret-key-here'
    MODEL_PATH = os.path.join(os.path.dirname(__file__), 'models/compliance_model.pkl')
    MODEL_COLUMNS_PATH = os.path.join(os.path.dirname(__file__), 'models/model_columns.pkl')
