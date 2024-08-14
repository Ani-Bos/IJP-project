package com.UKG.IJPmicroservices.Repositories;

import com.UKG.IJPmicroservices.Model.OpeningModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningInterface extends JpaRepository<OpeningModel, Long> {
}
