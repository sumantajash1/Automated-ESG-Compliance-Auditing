package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Role;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Repositories.ClientDao;
import com.sumanta.HackFest.Repositories.SupplierDao;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    ClientDao clientDao;
    @Autowired
    SupplierDao supplierDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String register(Client client) {
        //System.out.println(client);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setClientId(GenerateClientId());
        clientDao.save(client);
        return "Saved Successfully";
    }

    public String signIn(String clientId, String rawPassword) {
        var existing = clientDao.getByClientId(clientId);
        if(existing.isPresent()) {
            String encodedPassword = existing.get().getPassword();
            if(passwordEncoder.matches(rawPassword, encodedPassword)) {
                Role role = existing.get().getRole();
                return jwtTokenUtil.GenerateToken(clientId, role);
            }
        }
        return "Invalid Credentials";
    }

    private String GenerateClientId() {
        return "EID-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public boolean AlreadyExists(String gstNumber) {
        //System.out.println(clientDao.existsById(gstNumber));
        return clientDao.existsByGstNumber(gstNumber);

    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.findAll();
    }
}
