package com.sumanta.HackFest.Repositories;

import com.sumanta.HackFest.Entities.Client;
import com.sumanta.HackFest.Entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierDao extends JpaRepository<Supplier, String> {
    public Optional<Supplier> getByGstNumber(String gstNumber);
}
