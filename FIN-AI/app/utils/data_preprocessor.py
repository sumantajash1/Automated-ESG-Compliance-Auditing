import pandas as pd

def preprocess_input(input_df: pd.DataFrame, model_columns: list) -> pd.DataFrame:
    # Handle missing values by filling with zero
    processed = input_df.fillna(0)
    
    # Convert categorical columns to one-hot encoding (if any)
    processed = pd.get_dummies(processed)
    
    # Align columns with model's expected input
    missing_cols = set(model_columns) - set(processed.columns)
    for col in missing_cols:
        processed[col] = 0  # Add missing columns with 0 values
    processed = processed[model_columns]
    
    return processed
