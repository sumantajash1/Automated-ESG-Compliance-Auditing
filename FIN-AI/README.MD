# Financial Compliance Check Model

## Set Up

Navigate to FIN-AI directory first

```bash
cd FIN-AI
```

Create a virtual environment, activate it, and install the required dependencies:

```bash
python -m venv venv
venv\Scripts\activate     # For Windows
pip install -r requirements.txt
```


## Train the Model

Before using the API, run the training script:

```bash
python app/train_model.py
```

## Run the Project

Start the application using Uvicorn:

```bash
uvicorn app.main:app
```

To enable auto-reloading on file changes, run:

```bash
uvicorn app.main:app --reload
```

## Sample Input (Swagger UI)

Click on the localhost link
Go to /docs route
Click on the POST /predict endpoint, then click 'try it out' with the following JSON body

```json
{
  "data": [
    {
      "sales": 150000,
      "revenue": 180000,
      "expenses": 120000,
      "profit": 60000,
      "tax_paid": 15000
    },
    {
      "sales": 3000000,
      "revenue": 100000,
      "expenses": 50000,
      "profit": 250000,
      "tax_paid": 1000
    }
  ]
}
```

## Sample Output

```json
{
  "predictions": [1, -1]
}
```
