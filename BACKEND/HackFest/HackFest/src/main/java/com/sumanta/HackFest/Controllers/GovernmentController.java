package com.sumanta.HackFest.Controllers;

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

import java.util.List;

@RestController
@RequestMapping("/Government")
@EnableMethodSecurity
public class GovernmentController {
    @Autowired
    GovernmentService govService;
//    @Autowired
//    CookieUtil cookieUtil;

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody Government government) {
        return govService.register(government);
    }

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody Government government, HttpServletResponse response) {
        String govId = government.getEmployeeId();
        if(govService.isValidId(govId)) {
            String JwtToken = govService.SignIn(government);
            if("Invalid Credentials".equals(JwtToken)) {
                return ResponseEntity.ok("Wrong Password");
            } else {
                ResponseCookie cookie = CookieUtil.generateCookie(JwtToken);
                response.setHeader("jwt", JwtToken);
                return ResponseEntity.ok("Logged In Successfully");
            }
        }
        return ResponseEntity.ok("Invalid Government Id");
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
