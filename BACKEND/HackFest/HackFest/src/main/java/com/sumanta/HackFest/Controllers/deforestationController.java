package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.DeforestationData;
import com.sumanta.HackFest.Services.DeforestationService;
import com.sumanta.HackFest.Validators.DeforestationApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/supplier/deforestation")
public class deforestationController {

    @Autowired
    private DeforestationService deforestationService;

//    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/get/{supplierId}/{locationId}")
    public ResponseEntity<List<DeforestationData>> getDeforestation(@PathVariable String supplierId, @PathVariable String  locationId, @RequestParam int startYear, @RequestParam int endYear) {
        List<DeforestationData> deforestationData = deforestationService.getDeforestation(supplierId, locationId, startYear, endYear);

        if(deforestationData == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deforestationData, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/data")
    public ResponseEntity<String> deforestationData(@RequestBody DeforestationApiResponse deforestationApiResponse, @RequestParam String supplierId, @RequestParam String locationId) {
        System.out.println("I get deforestation data");
        deforestationService.saveApiResponse(deforestationApiResponse, supplierId, locationId); // Assuming you get these from somewhere
        return ResponseEntity.ok("Saved successfully!");
    }

//    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/test")
    public String test() {
        return deforestationService.fetchFromMicroservice("GST500000000", "1", 2021, 2023);
    }
}
