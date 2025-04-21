package com.sumanta.HackFest.Entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "deforestationdata")
public class DeforestationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "gstnumber")
    private String gstNumber;

    @Column(name = "locationId")
    private String locationId;

    @Column(name = "totalArea")
    private double totalArea;

    @Column(name = "initialForestArea")
    private double initialForestArea;

    @Column(name = "areaLost")
    private double areaLost;

    @Column(name = "isDeforestationDetected")
    private boolean isDeforestationDetected;

    @Column(name = "percentageLoss")
    private double percentageLoss;

    @Column(name = "startYear")
    private int startYear;

    @Column(name = "endYear")
    private int endYear;

    public DeforestationData(int id, String gstNumber, String locationId, double totalArea, double initialForestArea, double areaLost, boolean isDeforestationDetected, double percentageLoss, int startYear, int endYear) {
        this.id = id;
        this.gstNumber = gstNumber;
        this.locationId = locationId;
        this.totalArea = totalArea;
        this.initialForestArea = initialForestArea;
        this.areaLost = areaLost;
        this.isDeforestationDetected = isDeforestationDetected;
        this.percentageLoss = percentageLoss;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public DeforestationData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public boolean isDeforestationDetected() {
        return isDeforestationDetected;
    }

    public void setDeforestationDetected(boolean deforestationDetected) {
        isDeforestationDetected = deforestationDetected;
    }

    public double getInitialForestArea() {
        return initialForestArea;
    }

    public void setInitialForestArea(double initialForestArea) {
        this.initialForestArea = initialForestArea;
    }

    public double getAreaLost() {
        return areaLost;
    }

    public void setAreaLost(double areaLost) {
        this.areaLost = areaLost;
    }

    public double getPercentageLoss() {
        return percentageLoss;
    }

    public void setPercentageLoss(double percentageLoss) {
        this.percentageLoss = percentageLoss;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}

