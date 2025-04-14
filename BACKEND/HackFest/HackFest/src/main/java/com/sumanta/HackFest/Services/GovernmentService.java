package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.Government;
import com.sumanta.HackFest.Repositories.GovernmenttDao;
import com.sumanta.HackFest.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GovernmentService {
    @Autowired
    GovernmenttDao dao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public boolean isValidId(String EmployeeId) {
        return dao.existsById(EmployeeId);
    }

    public String register(Government government) {
        government.setPassword(passwordEncoder.encode(government.getPassword()));
        dao.save(government);
        return "Successfully registered government Id";
    }

    public String SignIn(Government government) {
        String EmployeeId = government.getEmployeeId();
        var existing = dao.getByEmployeeId(EmployeeId);
        if(existing.isPresent()) {
            if(passwordEncoder.matches(government.getPassword(), existing.get().getPassword())) {
                return jwtTokenUtil.GenerateToken(EmployeeId, existing.get().getRole());
            }
        }
        return "Invalid Credentials";
    }

}
