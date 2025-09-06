package com.sumanta.HackFest.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
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
}

