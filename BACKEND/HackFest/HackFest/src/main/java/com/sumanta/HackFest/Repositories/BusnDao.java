package com.sumanta.HackFest.Repositories;

import com.sumanta.HackFest.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusnDao extends JpaRepository<Client, String> {

}
