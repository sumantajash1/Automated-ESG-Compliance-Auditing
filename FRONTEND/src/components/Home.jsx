import React from "react";
import BannerBackground from "../Assets/home-banner-background.png";
import BannerImage from "../Assets/home-banner-image.png";
import Navbar from "./Navbar";
import { FiArrowRight } from "react-icons/fi";

const Home = () => {
  return (
    <div className="home-container">
      
      <Navbar />
      <br/><br/><br/>
      <div className="home-banner-container">
        <div className="home-bannerImage-container">
          <img  src={BannerBackground} alt="" 
            
          />
        </div>
        <div className="home-text-section">
          <h1 className="primary-heading">
            Transform supply chain transparency with AI driven ethical audits.
          </h1>
          <p className="primary-text">
          Detect deforestation, labor violations, and fraud in real-time using geospatial AI, blockchain, and machine learning.
          </p>
          <button className="secondary-button">
            Get Started <FiArrowRight />{" "}
          </button>
        </div>
        <div className="home-image-section">
          <img src={BannerImage} alt=""
          style={{
            
            height: "53vh",
            width: "50vw",
          }} />
        </div>
      </div>
    </div>
  );
};

export default Home;
