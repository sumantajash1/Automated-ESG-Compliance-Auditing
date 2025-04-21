package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.DeforestationData;
import com.sumanta.HackFest.Repositories.DeforestationDao;
import com.sumanta.HackFest.Validators.DeforestationApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeforestationService {

    @Autowired
    private DeforestationDao deforestationDao;

    @Autowired
    private WebClient webClient;

    public List<DeforestationData> getDeforestation(String supplierId, String locationId, int startYear, int endYear) {
        List<DeforestationData> deforestationDataList = deforestationDao.findByGstNumberAndLocationIdAndStartYearBetweenAndEndYearBetween(supplierId, locationId, startYear, endYear, startYear, endYear);

        if (deforestationDataList.isEmpty()) {
            return null;
        }

        return deforestationDataList;
    }

    public String fetchFromMicroservice(String supplierId, String locationId, int startYear, int endYear) {
        // Prepare request body
        Map<String, Object> requestBody = getStringObjectMap();


        // Use the new WebClient interface to make the call
        Mono<String> responseMono = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("127.0.0.1")
                        .port(8000)
                        .path("/deforestation/check")
                        .queryParam("callback_url", "http://localhost:8080/Supplier/deforestation/data")
                        .queryParam("start_year", startYear)
                        .queryParam("end_year", endYear)
                        .queryParam("supplier_id", supplierId)
                        .queryParam("location_id", locationId)
                        .build())
                .header("HACKFEST-AI", "r9zL5N4hnfw6YA_x1qzlC2PnBsINfrJdFM8lAGQW_z4")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);

        // Block for the result in non-reactive service method
        String ackMessage = responseMono.block();

        System.out.println("ackMessage" + ackMessage);
        
        return ackMessage;
    }

    private static Map<String, Object> getStringObjectMap() {
        Map<String, Object> polygon = new HashMap<>();
        polygon.put("type", "Polygon");

        List<List<List<Double>>> coordinates = List.of(
                List.of(
                        List.of(87.29829122863777, 22.426278655942966),
                        List.of(87.29305225494488, 22.419841932069417),
                        List.of(87.30127545415945, 22.420475400672203),
                        List.of(87.30047966068719, 22.424296585466195),
                        List.of(87.29829122863777, 22.426278655942966)
                )
        );
        polygon.put("coordinates", coordinates);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("polygon", polygon);
        return requestBody;
    }

    public void saveApiResponse(DeforestationApiResponse apiResponse, String gstNumber, String locationId) {
        List<DeforestationApiResponse.DeforestationResults> results = apiResponse.getData();

        for (DeforestationApiResponse.DeforestationResults result : results) {
            DeforestationData data = new DeforestationData();
            data.setGstNumber(gstNumber);
            data.setLocationId(locationId);
            data.setTotalArea(apiResponse.getTotalAreaM2());
            data.setInitialForestArea(result.getInitialForestAreaM2());
            data.setAreaLost(result.getAreaLostMeterSquare());
            data.setDeforestationDetected(result.isDeforestationDetected());
            data.setPercentageLoss(result.getPercentageLoss());
            data.setStartYear(result.getStartYear());
            data.setEndYear(result.getEndYear());

            deforestationDao.save(data);
        }
    }
}
