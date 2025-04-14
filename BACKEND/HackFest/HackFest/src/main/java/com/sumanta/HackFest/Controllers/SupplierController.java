package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Services.GstService;
import com.sumanta.HackFest.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if(gstService.VerifyGstNumber(supplier.getGstNumber())) {
            //method in service for checking if already exists
            //supplier.setRole();
            return supplierService.register(supplier);
        }
        return "idk";
    }

    @PostMapping("/Login")
    public String Login(@RequestBody Supplier supplier) {
        return supplierService.SignIn(supplier);
    }
}
