from fastapi import Request, HTTPException
from dotenv import load_dotenv
import os

load_dotenv()

API_KEY_NAME = os.getenv("API_KEY_NAME_FOR_SERVER")
API_KEY = os.getenv("API_KEY_FOR_SERVER")

# print("API_KEY_NAME:", API_KEY_NAME)
# print("API_KEY:", API_KEY)


def verify_api_key(request: Request):
    incoming_api_key = request.headers.get(API_KEY_NAME)
    if not incoming_api_key:
        raise HTTPException(status_code=403, detail="API Key is missing")
    if incoming_api_key != API_KEY:
        raise HTTPException(status_code=403, detail="Invalid API Key")
    return True
