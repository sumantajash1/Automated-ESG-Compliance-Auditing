package com.sumanta.HackFest.Repositories;

import com.sumanta.HackFest.Entities.Government;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GovernmenttDao extends JpaRepository<Government, String> {
    Optional<Government>getByEmployeeId(String employeeId);
}
