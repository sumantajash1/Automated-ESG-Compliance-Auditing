package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.DTO.SupplierDto;
import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Services.ClientService;
import com.sumanta.HackFest.Services.GstService;
import com.sumanta.HackFest.Utils.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<String> Login(@RequestBody Client client, HttpServletResponse response) {
        String clientId = client.getClientId();
        String password = client.getPassword();
        String JwtToken = clientService.SignIn(clientId, password);
        if("Invalid Credentials".equals(JwtToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JwtToken);
        }
        ResponseCookie cookie = CookieUtil.generateCookie(JwtToken);
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        //System.out.println(cookie);
        return ResponseEntity.ok(JwtToken);
    }

    @GetMapping("/getAllSuppliers")
    @PreAuthorize("hasRole('CLIENT')")
    public List<SupplierDto> getAllMySuppliers() {
        List<Supplier> AllSuppliers = clientService.getAllSuppliers();
        List<SupplierDto> result = new ArrayList<>();
        for(Supplier supplier : AllSuppliers) {
            SupplierDto dto = new SupplierDto(
                    supplier.getGstNumber(),
                    supplier.getSupplierName(),
                    supplier.getEmail(),
                    supplier.getContactNumber()
            );
            result.add(dto);
        }
        return result;
    }
}
