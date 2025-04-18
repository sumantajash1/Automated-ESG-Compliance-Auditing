from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)

def test_prediction_endpoint():
    payload = {
        "data": [
            {"sales": 100000, "revenue": 120000, "expenses": 85000, "profit": 35000, "tax_paid": 7000},
            {"sales": 102000, "revenue": 122000, "expenses": 87000, "profit": 35000, "tax_paid": 7100}
        ]
    }
    
    response = client.post("/predict", json=payload)
    
    assert response.status_code == 200
    assert "predictions" in response.json()
    assert isinstance(response.json()["predictions"], list)
