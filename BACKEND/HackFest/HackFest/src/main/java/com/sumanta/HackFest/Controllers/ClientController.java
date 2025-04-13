package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Client")
@EnableMethodSecurity
public class ClientController {
    @Autowired
    ClientService service;

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody Client client) {
        if(service.verifyGstNumber(client.getGstNumber())) {
            if(service.AlreadyExists(client.getGstNumber())) {
                return "Already Registered";
            } else {
                return service.register(client);
            }
        }
        return "idk";
    }

    @PostMapping("/Login")
    public String Login(@RequestBody Client client) {
        String gstNumber = client.getGstNumber();
        String password = client.getPassword();
        return service.SignIn(gstNumber, password);
    }


    @GetMapping("/getSuppliers")
    @PreAuthorize("hasRole('CLIENT')")
    public String getAllMySuppliers() {
        return "Getting All of the suppliers";
    }
}
