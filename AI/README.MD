# AI - Hackfest'25

## Set Up

Create a virtual environment, activate it, and install the required dependencies:

```bash
python3 -m venv venv
source venv/bin/activate  # For Linux/macOS
venv\Scripts\activate     # For Windows
pip3 install -r requirements.txt
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

## Update Dependencies

After installing any new packages, update the `requirements.txt` file:

```bash
pip freeze > requirements.txt
```

## API Endpoint: 1. Deforestation Check

Make a **POST** request to:

```http
/deforestation/check
```

### Headers

```http
API_KEY_NAME: API_KEY
```

### Query Parameters

```text
callback_url: <your_callback_url>
start_year: <start_year>
end_year: <end_year>
```

### Request Body (one of the following)

#### Polygon

```json
{
  "polygon": {
    "type": "Polygon",
    "coordinates": [[[0]]]
  }
}
```

#### MultiPolygon

```json
{
  "multipolygon": {
    "type": "MultiPolygon",
    "coordinates": [[[[0]]]]
  }
}
```

#### Circle

```json
{
  "circle": {
    "type": "Point",
    "coordinates": [0],
    "radius": 0
  }
}
```

#### Bounding Box

```json
{
  "bbox": {
    "bbox": [0, 0, 0, 0]
  }
}
```

### Response

After making the request, the result will be sent to your specified `callback_url` via a POST request once processing is complete.
