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

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        dao.save(client);
        return "Saved Successfully";
    }

    public String SignIn(String gstNumber, String rawPassword) {
        var existing = dao.getByGstNumber(gstNumber);
        if(existing.isPresent()) {
            String encodedPassword = existing.get().getPassword();
            if(passwordEncoder.matches(rawPassword, encodedPassword)) {
                Role role = existing.get().getRole();
                return jwtTokenUtil.GenerateToken(gstNumber, role);
            }
        }
        return "Invalid Credentials";
    }

    public boolean verifyGstNumber(String gstNumber) {
        //maybe use a dummy data later
        return true;
    }

    public boolean AlreadyExists(String gstNumber) {
        return dao.existsById(gstNumber);
    }
}
