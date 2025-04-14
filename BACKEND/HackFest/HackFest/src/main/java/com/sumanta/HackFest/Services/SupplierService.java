package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Repositories.SupplierDao;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    SupplierDao dao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String register(Supplier supplier) {
        supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
        dao.save(supplier);
        return "Supplier Registered Successfully";
    }

    public String SignIn(Supplier supplier) {
        String gstNumber = supplier.getGstNumber();
        var existing = dao.getByGstNumber(gstNumber);
        if(existing.isPresent()) {
            if(passwordEncoder.matches(supplier.getPassword(), existing.get().getPassword())) {
                return jwtTokenUtil.GenerateToken(gstNumber, existing.get().getRole());
            }
        }
        return "Invalid Credentials";
    }

}
