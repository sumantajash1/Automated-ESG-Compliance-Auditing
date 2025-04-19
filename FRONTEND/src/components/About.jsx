import React from "react";
import Aboutimg from "../Assets/Aboutimg.jpg";
// import AboutBackgroundImage from "../Assets/about-background.png";
import { BsFillPlayCircleFill } from "react-icons/bs";
import { FaGlobeAmericas, FaExclamationTriangle, FaBrain, FaLock } from "react-icons/fa";


const About = () => {
  return (
    <div className="about-section-container">
      <div className="about-background-image-container">
        {/* <img className="Mainimg" src={AboutBackgroundImage} alt="" /> */}
      </div>
      <div className="about-section-image-container">
        <img src={Aboutimg} alt="" 
        style={{
          borderRadius: "50%",
          height: "53vh",
            width: "50vw",
         
        }}/>
      </div>
      <div className="about-section-text-container">
        <p className="primary-subheading">About</p>
        <h1 className="primary-heading">
        Enhancing Transparency Across Global Supply Chains
        </h1>
        <p className="primary-text flex items-start gap-2">
          <FaExclamationTriangle className="text-red-500 mt-1" />
          Global supply chains face scrutiny for environmental breaches, labor abuse, and fraud — often overlooked due to outdated or unreliable self-reported audit data.
        </p>

        <p className="primary-text flex items-start gap-2">
          <FaBrain className="text-green-600 mt-1" />
          Our AI-powered auditing system uses geospatial intelligence, machine learning, and blockchain to enable real-time risk detection and full supply chain transparency.


        </p>

        {/* <div className="about-buttons-container">
          <button className="secondary-button">Learn More</button>
          <button className="watch-video-button">
            <BsFillPlayCircleFill /> Watch Video
          </button>
        </div> */}
      </div>
    </div>
  );
};

export default About;
