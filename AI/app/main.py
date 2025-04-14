# app/main.py

from fastapi import FastAPI
from app.routes import deforestationRoute


app = FastAPI(title="Hackfest Python Microservice API", version="1.0")

# Include your routes
app.include_router(deforestationRoute.router)
