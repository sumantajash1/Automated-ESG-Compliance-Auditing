import pandas as pd
import numpy as np
from sklearn.ensemble import IsolationForest
from joblib import dump

# Sample training data (replace with actual compliance data)
data = pd.DataFrame({
    'sales': np.random.randint(50000, 500000, 1000),
    'revenue': np.random.randint(50000, 500000, 1000),
    'expenses': np.random.randint(20000, 400000, 1000),
    'profit': np.random.randint(1000, 100000, 1000),
    'tax_paid': np.random.randint(100, 10000, 1000)
})

# Train the anomaly detection model
model = IsolationForest(n_estimators=100, contamination=0.01)
model.fit(data)

# Save the trained model and column names
dump(model, 'models/compliance_model.pkl')
dump(list(data.columns), 'models/model_columns.pkl')

print("✅ Model and columns saved successfully!")
