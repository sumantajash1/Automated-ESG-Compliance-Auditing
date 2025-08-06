import React from "react";
import WorldMap from "../Assets/WorldMap.png";
import Blockchain from "../Assets/Blockchain.png";
import DeliveryMeals from "../Assets/delivery-image.png";

const Work = () => {
  const workInfoData = [
    {
      image: WorldMap,
      title: "🌍 Geospatial AI Monitoring",
      text: "Satellite imagery detects deforestation, illegal land use, and carbon emissions.",
    },
    {
      image: Blockchain,
      title: "🔗 Blockchain Labor Ledger",
      text: " Immutable records verify fair wages and working hours, eliminating child labor risks. ",
    },
    {
      image: DeliveryMeals,
      title: "🕵️ ML Fraud Detection",
      text: "NLP analyzes contracts for hidden risks; predictive models flag suspicious invoices.",
    },
    {
      image: DeliveryMeals,
      title: "📊 Real-Time Compliance Dashboards",
      text: "Integrate with ERP systems for live sustainability scores and corrective actions",
    },
  ];
  return (
    <div className="work-section-wrapper">
      <div className="work-section-top">
        <p className="primary-subheading">Work</p>
        <h1 className="primary-heading">How Our System Works</h1>
        
      </div>
      <div className="work-section-bottom ">
        {workInfoData.map((data) => (
          <div className="work-section-info" key={data.title}>
            <div className="info-boxes-img-container">
              <img src={data.image} alt=""  />
            </div>
            <h2>{data.title}</h2>
            <p>{data.text}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Work;
