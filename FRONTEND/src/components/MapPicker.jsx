// import React, { useState } from "react";
// import { MapContainer, TileLayer, FeatureGroup, Polygon } from "react-leaflet";
// import { EditControl } from "react-leaflet-draw";
// import type {
//   Feature,
//   Geometry,
//   Polygon as GeoPolygon,
//   MultiPolygon,
// } from "geojson";

// type GeoJsonPayload =
//   | { polygon: GeoPolygon }
//   | { multipolygon: MultiPolygon }
//   | { circle: { type: "Point"; coordinates: [number, number]; radius: number } }
//   | { bbox: { bbox: [number, number, number, number] } };

// export const MapPicker: React.FC = () => {
//   const [payload, setPayload] = useState<GeoJsonPayload | null>(null);

//   const onCreated = (e) => {
//     const { layerType, layer } = e;
//     if (layerType === "polygon") {
//       const geom = (layer.toGeoJSON() as Feature<GeoPolygon>).geometry;
//       if (payload && "polygon" in payload) {
//         setPayload({
//           multipolygon: {
//             type: "MultiPolygon",
//             coordinates: [payload.polygon.coordinates, geom.coordinates],
//           },
//         });
//       } else {
//         setPayload({ polygon: geom });
//       }
//     }

//     if (layerType === "rectangle") {
//       const bounds = layer.getBounds();
//       const bb: [number, number, number, number] = [
//         bounds.getWest(),
//         bounds.getSouth(),
//         bounds.getEast(),
//         bounds.getNorth(),
//       ];
//       setPayload({ bbox: { bbox: bb } });
//     }

//     if (layerType === "circle") {
//       const center = layer.getLatLng();
//       const radius = layer.getRadius();
//       setPayload({
//         circle: {
//           type: "Point",
//           coordinates: [center.lng, center.lat],
//           radius,
//         },
//       });
//     }
//   };

//   const sendToBackend = async () => {
//     if (!payload) return alert("Draw something first! 🖌️");
//     await fetch("/api/geojson", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(payload),
//     });
//     console.log("Sent:", payload);
//   };

//   return (
//     <div style={{ height: "100vh" }}>
//       <MapContainer
//         center={[20.5937, 78.9629]}
//         zoom={5}
//         style={{ height: "100%" }}
//       >
//         <TileLayer
//           // OpenStreetMap is community‑updated daily (= latest)
//           url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
//           attribution="© OpenStreetMap contributors"
//         />
//         <FeatureGroup>
//           <EditControl
//             position="topright"
//             onCreated={onCreated}
//             draw={{
//               polygon: true,
//               rectangle: true,
//               circle: true,
//               polyline: false,
//               marker: false,
//               circlemarker: false,
//             }}
//           />
//         </FeatureGroup>
//       </MapContainer>

//       <button
//         onClick={sendToBackend}
//         style={{
//           position: "absolute",
//           top: 10,
//           left: 10,
//           padding: "0.5rem 1rem",
//           background: "#1e40af",
//           color: "white",
//           borderRadius: "0.25rem",
//         }}
//       >
//         Send GeoJSON
//       </button>

//       <pre
//         style={{
//           position: "absolute",
//           bottom: 10,
//           left: 10,
//           background: "rgba(255,255,255,0.8)",
//           padding: "1rem",
//           maxWidth: "30%",
//           overflow: "auto",
//         }}
//       >
//         {payload ? JSON.stringify(payload, null, 2) : "Draw a shape!"}
//       </pre>
//     </div>
//   );
// };

import React, { useEffect } from "react";
import L from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-draw";
import "leaflet-draw/dist/leaflet.draw.css";
import "../style/map.css"

export const MapPicker = ({ onShapeDrawn }) => {
  useEffect(() => {
    const map = L.map("map").setView([20, 80], 5);

    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      maxZoom: 18,
    }).addTo(map);

    const drawnItems = new L.FeatureGroup();
    map.addLayer(drawnItems);

    const drawControl = new L.Control.Draw({
      draw: {
        polygon: true,
        rectangle: true,
        circle: true,
        circlemarker: false,
        marker: false,
        polyline: false,
      },
      edit: {
        featureGroup: drawnItems,
      },
    });

    map.addControl(drawControl);

    map.on("draw:created", function (e) {
      const layer = e.layer;
      const type = e.layerType;

      drawnItems.addLayer(layer);

      let result = null;

      if (type === "polygon") {
        const coords = layer.getLatLngs()[0].map((p) => [p.lng, p.lat]);
        coords.push(coords[0]); // Close the loop
        result = {
          polygon: {
            type: "Polygon",
            coordinates: [coords],
          },
        };
      }

      if (type === "rectangle") {
        const bounds = layer.getBounds();
        const southWest = bounds.getSouthWest();
        const northEast = bounds.getNorthEast();

        result = {
          bbox: {
            bbox: [southWest.lng, southWest.lat, northEast.lng, northEast.lat],
          },
        };
      }

      if (type === "circle") {
        const center = layer.getLatLng();
        result = {
          circle: {
            type: "Point",
            coordinates: [center.lng, center.lat],
            radius: layer.getRadius(),
          },
        };
      }

      // You can later add multipolygon support here manually if needed

      console.log("Drawn shape:", result);
      if (onShapeDrawn) onShapeDrawn(result);
    });

    return () => {
      map.remove();
    };
  }, [onShapeDrawn]);

  return (
    <>
      {/* <div id="hii" className="h-[120px] w-screen"></div> */}
      <div
        id="map"
        style={{ height: "100vh", width: "100vw", padding: "0 0 0 0" }}
      />
    </>
  );
};
