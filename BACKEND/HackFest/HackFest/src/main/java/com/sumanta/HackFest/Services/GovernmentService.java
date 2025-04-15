package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Government;
import com.sumanta.HackFest.Entities.Supplier;
import com.sumanta.HackFest.Repositories.ClientDao;
import com.sumanta.HackFest.Repositories.GovernmenttDao;
import com.sumanta.HackFest.Repositories.SupplierDao;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernmentService {
    @Autowired
    GovernmenttDao governmenttDao;
    @Autowired
    SupplierDao supplierDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public boolean isValidId(String EmployeeId) {
        return governmenttDao.existsById(EmployeeId);
    }

    public String register(Government government) {
        government.setPassword(passwordEncoder.encode(government.getPassword()));
        governmenttDao.save(government);
        return "Successfully registered government Id";
    }

    public String SignIn(Government government) {
        String EmployeeId = government.getEmployeeId();
        var existing = governmenttDao.getByEmployeeId(EmployeeId);
        if(existing.isPresent()) {
            if(passwordEncoder.matches(government.getPassword(), existing.get().getPassword())) {
                return jwtTokenUtil.GenerateToken(EmployeeId, existing.get().getRole());
            }
        }
        return "Invalid Credentials";
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.findAll();
    }

    public List<Client> getAllClients() {
        return clientDao.findAll();
    }
}
