package com.sumanta.HackFest.Repositories;

import com.sumanta.HackFest.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDao extends JpaRepository<Client, String> {
    public Optional<Client> getByGstNumber(String gstNumber);

    public Optional<Client> getByClientId(String ClientId);

    public boolean existsByGstNumber(String gstNumber);
}
