package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Repositories.ClientDao;
import com.sumanta.HackFest.Repositories.SupplierDao;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierDao supplierDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String register(Supplier supplier) {
        supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
        supplierDao.save(supplier);
        return "Supplier Registered Successfully";
    }

    public String SignIn(Supplier supplier) {
        String gstNumber = supplier.getGstNumber();
        var existing = supplierDao.getByGstNumber(gstNumber);
        if(existing.isPresent()) {
            if(passwordEncoder.matches(supplier.getPassword(), existing.get().getPassword())) {
                return jwtTokenUtil.GenerateToken(gstNumber, existing.get().getRole());
            }
        }
        return "Invalid Credentials";
    }

    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    public boolean alreadyExists(String gstNumber) {
        return supplierDao.existsByGstNumber(gstNumber);
    }
}
