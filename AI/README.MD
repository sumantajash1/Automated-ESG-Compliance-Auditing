# AI - Hackfest'25

## Set Up

Go inside AI folder

```bash
    cd AI
```

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

After making the request, the result will be sent to your specified `callback_url` via a POST request once processing is complete. All area in square meter.

```json
{
  "total_area_m2": 322296.2895344203,
  "deforestation_results": [
    {
      "Initial_forest_area_m2": 293054.5244810518,
      "area_lost_meter_square": 2674.1388763726927,
      "deforestation_detected": true,
      "percentage_loss": 0.9125055759190629,
      "startYear": "2021",
      "endYear": "2022"
    },
    {
      "Initial_forest_area_m2": 295371.1028354422,
      "area_lost_meter_square": 0,
      "deforestation_detected": false,
      "percentage_loss": 0,
      "startYear": "2022",
      "endYear": "2023"
    }
  ]
}
```
