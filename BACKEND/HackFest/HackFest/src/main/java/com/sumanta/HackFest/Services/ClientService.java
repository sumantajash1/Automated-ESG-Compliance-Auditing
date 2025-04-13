package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Role;
import com.sumanta.HackFest.Repositories.BusnDao;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    BusnDao dao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String register(Client client) {

        dao.save(client);
        return "Saved Successfully";
    }

    public String SignIn(String gstNumber, String rawPassword, String role) {
        var existing = dao.getByGstNumber(gstNumber);
        if(existing.isPresent()) {
            String encodedPassword = existing.get().getPassword();
            if(passwordEncoder.matches(rawPassword, encodedPassword)) {
                return jwtTokenUtil.GenerateToken(gstNumber, Role.valueOf(role));
            }
        }
        return "Invalid Credentials";
    }
}
