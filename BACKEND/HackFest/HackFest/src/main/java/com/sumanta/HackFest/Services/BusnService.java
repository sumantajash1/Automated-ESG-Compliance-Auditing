package com.sumanta.HackFest.Services;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Repositories.BusnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusnService {
    @Autowired
    BusnDao dao;

    public String register(Client business) {
        dao.save(business);
        return "Saved Successfully";
    }
}
