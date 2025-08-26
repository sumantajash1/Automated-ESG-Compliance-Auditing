package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.DTO.ClientDto;
import com.sumanta.HackFest.DTO.GovernmentDto;
import com.sumanta.HackFest.DTO.SupplierDto;
import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Government;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Services.GovernmentService;
import com.sumanta.HackFest.Utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/government")
@EnableMethodSecurity
public class GovernmentController {
    @Autowired
    GovernmentService govService;
//    @Autowired
//    CookieUtil cookieUtil;

    @PostMapping("/sign-up")
    public String SignUp(@RequestBody Government government) {
        return govService.register(government);
    }

    @PostMapping("/log-in")
    public ResponseEntity<String> Login(@RequestBody Government government, HttpServletResponse response) {
        String govId = government.getEmployeeId();
        if(govService.isValidId(govId)) {
            String JwtToken = govService.SignIn(government);
            if("Invalid Credentials".equals(JwtToken)) {
                return ResponseEntity.ok("Wrong Password");
            } else {
                ResponseCookie cookie = CookieUtil.generateCookie(JwtToken);
                response.setHeader("jwt", JwtToken);
                return ResponseEntity.ok(JwtToken);
                //return ResponseEntity.ok("Logged In Successfully");
            }
        }
        return ResponseEntity.ok("Invalid Government Id");
    }

    @GetMapping("/get-all-suppliers")
    @PreAuthorize("hasRole('GOVERNMENT')")
    public List<SupplierDto> GetAllSuppliers() {
        List<Supplier> AllSuppliers = govService.getAllSuppliers();
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

    @GetMapping("/get-all-clients")
    @PreAuthorize("hasRole('GOVERNMENT')")
    public List<ClientDto> getAllClients() {
        List<Client> AllClients = govService.getAllClients();
        List<ClientDto> result = new ArrayList<>();
        for(Client client : AllClients) {
            ClientDto dto = new ClientDto(
                    client.getClientId(),
                    client.getclientName(),
                    client.getEmail(),
                    client.getContactNumber()
            );
            result.add(dto);
        }
        return result;
    }
}
