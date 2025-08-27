package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.DTO.ClientDto;
import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Services.GstService;
import com.sumanta.HackFest.Services.SupplierService;
import com.sumanta.HackFest.Utils.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    GstService gstService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody Supplier supplier) {
        String gstNumber = supplier.getGstNumber();
        if(gstService.VerifyGstNumber(gstNumber)) {
            if(supplierService.alreadyExists(gstNumber)) {
                return "Supplier Already Registered";
            }
            return supplierService.register(supplier);
        }
        return "idk";
    }

    @PostMapping("log-in")
    public ResponseEntity<String> logIn(@RequestBody Supplier supplier, HttpServletResponse response) {
        if(!gstService.VerifyGstNumber(supplier.getGstNumber())) {
            ResponseEntity.ok("Invalid Gst Number");
        }
        String jwtToken = supplierService.SignIn(supplier);
        if(jwtToken.equals("Invalid Credentials")) {
            return ResponseEntity.ok("Invalid Credentials");
        }
        ResponseCookie cookie = CookieUtil.generateCookie(jwtToken);
        response.setHeader("jwt", jwtToken);
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(jwtToken);
        //return ResponseEntity.ok("Login Successful");
    }

    @GetMapping("/get-all-clients")
    @PreAuthorize("hasRole('SUPPLIER')")
    public List<ClientDto> getAllClients() {
        List<Client> AllClients = supplierService.getAllClients();
        List<ClientDto> result = new ArrayList<>();
        for(Client client : AllClients) {
            ClientDto dto = new ClientDto();
            dto.setClientName(client.getclientName());
            dto.setClientId(client.getClientId());
            dto.setContact(client.getContactNumber());
            dto.setEmail(client.getEmail());
            result.add(dto);
        }
        return result;
    }

}