package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Services.BusnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Business")
public class BusnController {
    @Autowired
    BusnService service;

//    @GetMapping("/")
//    public String Home() {
        //testing purpose
//        return "Home called";
//    }

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody Client business) {
        //to write a method for checking if already user exists ?
        //to write a method to check if a gst number is valid ? (using dummy numbers/always return true for now)
        return service.register(business);
    }

    @GetMapping("Login")
    public String Login(@RequestParam("GstNumber") String gstNumber, @RequestParam("password") String password) {
        return "";
    }
}
