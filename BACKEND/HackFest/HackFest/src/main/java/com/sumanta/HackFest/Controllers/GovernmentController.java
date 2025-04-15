package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Government;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Services.GovernmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Government")
@EnableMethodSecurity
public class GovernmentController {
    @Autowired
    GovernmentService govService;

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody Government government) {
        return govService.register(government);
    }

    @PostMapping("/Login")
    public String Login(@RequestBody Government government) {
        if(govService.isValidId(government.getEmployeeId())) {
            return govService.SignIn(government);
        }
        return "idk";
    }

    @GetMapping("/getAllSuppliers")
    @PreAuthorize("hasRole('GOVERNMENT')")
    public List<Supplier> GetAllSuppliers() {
        return govService.getAllSuppliers();
    }

    @GetMapping("/getAllClients")
    @PreAuthorize("hasRole('GOVERNMENT')")
    public List<Client> getAllClients() {
        return govService.getAllClients();
    }
}
