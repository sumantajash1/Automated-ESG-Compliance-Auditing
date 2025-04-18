from fastapi import FastAPI
from app.routes.api import api_bp


def create_app():
    app = FastAPI()
    
    @app.get("/")
    def root():
        return {"message": "API is up and running!"}
    

    app.include_router(api_bp)
    return app

app = create_app()

if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=5000)
