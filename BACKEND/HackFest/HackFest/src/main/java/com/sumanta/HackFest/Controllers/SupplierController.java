package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Repositories.ClientDao;
import com.sumanta.HackFest.Services.ClientService;
import com.sumanta.HackFest.Services.GstService;
import com.sumanta.HackFest.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/Supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    GstService gstService;


    @PostMapping("/SignUp")
    public String SignUp(@RequestBody Supplier supplier) {
        String gstNumber = supplier.getGstNumber();
        if(gstService.VerifyGstNumber(gstNumber)) {
            if(supplierService.alreadyExists(gstNumber)) {
                return "Supplier Already Registered";
            }
            return supplierService.register(supplier);
        }
        return "idk";
    }

    @PostMapping("/Login")
    public String Login(@RequestBody Supplier supplier) {
        return supplierService.SignIn(supplier);
    }

    @GetMapping("/getAllClients")
    @PreAuthorize("hasRole('SUPPLIER')")
    public List<Client> getAllClients() {
        return supplierService.getAllClients();
    }

}
