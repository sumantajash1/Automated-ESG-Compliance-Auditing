package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Services.ClientService;
import com.sumanta.HackFest.Services.GstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Client")
@EnableMethodSecurity
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    GstService gstService;

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody Client client) {
        String gstNumber = client.getGstNumber();
        if(gstService.VerifyGstNumber(gstNumber)) {
            if(clientService.AlreadyExists(gstNumber)) {
                return "Client Already Registered";
            } else {
                return clientService.register(client);
            }
        }
        return "Invalid Gst Number";
    }

    @PostMapping("/Login")
    public String Login(@RequestBody Client client) {
        String clientId = client.getClientId();
        String password = client.getPassword();
        return clientService.SignIn(clientId, password);
    }

    @GetMapping("/getAllSuppliers")
    @PreAuthorize("hasRole('CLIENT')")
    public List<Supplier> getAllMySuppliers() {
        return clientService.getAllSuppliers();
    }
}
