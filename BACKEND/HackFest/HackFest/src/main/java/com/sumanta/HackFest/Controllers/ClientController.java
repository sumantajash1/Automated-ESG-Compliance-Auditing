package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Business")
public class ClientController {
    @Autowired
    ClientService service;

//    @GetMapping("/")
//    public String Home() {
        //testing purpose
//        return "Home called";
//    }

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody Client business) {
        //to write a method for checking if already user exists in UserService ?
        //to write a method to check if a gst number is valid in UserService? (using dummy numbers/always return true for now)
        return service.register(business);
    }

    @PostMapping("/Login")
    public String Login(@RequestBody Client client) {
        String gstNumber = client.getGstNumber();
        String password = client.getPassword();
        String role = String.valueOf(client.getRole());
        String token =  service.SignIn(gstNumber, password, role);
        System.out.println(token);
        //return service.SignIn(gstNumber, password, role);
        return token;
    }
}
