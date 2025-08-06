package com.sumanta.HackFest.Validators;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DeforestationApiResponse {
    @JsonProperty("total_area_m2")
    private double totalAreaM2;

    @JsonProperty("deforestation_results")
    private List<DeforestationResults> data;

    public double getTotalAreaM2() {
        return totalAreaM2;
    }

    public void setTotalAreaM2(double totalAreaM2) {
        this.totalAreaM2 = totalAreaM2;
    }

    public List<DeforestationResults> getData() {
        return data;
    }

    public void setData(List<DeforestationResults> data) {
        this.data = data;
    }

    public static class DeforestationResults {
        @JsonProperty("Initial_forest_area_m2")
        private double initialForestAreaM2;

        @JsonProperty("area_lost_meter_square")
        private double areaLostMeterSquare;

        @JsonProperty("deforestation_detected")
        private boolean isDeforestationDetected;

        @JsonProperty("percentage_loss")
        private double percentageLoss;

        @JsonProperty("startYear")
        private int startYear;

        @JsonProperty("endYear")
        private int endYear;

        public int getEndYear() {
            return endYear;
        }

        public void setEndYear(int endYear) {
            this.endYear = endYear;
        }

        public int getStartYear() {
            return startYear;
        }

        public void setStartYear(int startYear) {
            this.startYear = startYear;
        }

        public double getPercentageLoss() {
            return percentageLoss;
        }

        public void setPercentageLoss(double percentageLoss) {
            this.percentageLoss = percentageLoss;
        }

        public boolean isDeforestationDetected() {
            return isDeforestationDetected;
        }

        public void setDeforestationDetected(boolean deforestationDetected) {
            isDeforestationDetected = deforestationDetected;
        }

        public double getAreaLostMeterSquare() {
            return areaLostMeterSquare;
        }

        public void setAreaLostMeterSquare(double areaLostMeterSquare) {
            this.areaLostMeterSquare = areaLostMeterSquare;
        }

        public double getInitialForestAreaM2() {
            return initialForestAreaM2;
        }

        public void setInitialForestAreaM2(double initialForestAreaM2) {
            this.initialForestAreaM2 = initialForestAreaM2;
        }
    }
}
