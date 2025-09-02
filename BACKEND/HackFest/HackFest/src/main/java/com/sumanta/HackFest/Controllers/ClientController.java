package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.DTO.ApiResponse;
import com.sumanta.HackFest.DTO.AuthResponseDto;
import com.sumanta.HackFest.DTO.SupplierDto;
import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Services.ClientService;
import com.sumanta.HackFest.Services.GstService;
import com.sumanta.HackFest.Services.SupplierService;
import com.sumanta.HackFest.Utils.CookieUtil;
import jakarta.servlet.http.HttpServlet;
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
@RequestMapping("/client")
@EnableMethodSecurity
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    GstService gstService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<AuthResponseDto>> signUp(@RequestBody Client client, HttpServletResponse response) {
        ApiResponse<AuthResponseDto> serviceResponse = clientService.register(client);
        if(serviceResponse.isSuccess()) {
            String jwtToken = serviceResponse.getData().getJwtToken();
            response.setHeader("jwtToken", jwtToken);
            response.setHeader(HttpHeaders.SET_COOKIE, CookieUtil.generateCookie(jwtToken).toString());
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<ApiResponse<AuthResponseDto>> logIn(@RequestBody Client client, HttpServletResponse response) {
        ApiResponse<AuthResponseDto> serviceResponse = clientService.signIn(client);
        if(serviceResponse.isSuccess())  {
            String jwtToken = serviceResponse.getData().getJwtToken();
            response.setHeader(HttpHeaders.SET_COOKIE, CookieUtil.generateCookie(jwtToken).toString());
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @GetMapping("/get-all-supplier")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ApiResponse<List<SupplierDto>>> getAllMySuppliers() {
        ApiResponse<List<SupplierDto>> serviceResponse = supplierService.getAllSuppliers();
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
