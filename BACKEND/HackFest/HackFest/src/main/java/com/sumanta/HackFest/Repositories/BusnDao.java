package com.sumanta.HackFest.Repositories;

import com.sumanta.HackFest.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusnDao extends JpaRepository<Client, String> {
    public Optional<Client> getByGstNumber(String gstNumber);
}
