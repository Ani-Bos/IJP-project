package com.UKG.IJPmicroservices.Repository;

import com.UKG.IJPmicroservices.Model.OpeningModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningRepository extends JpaRepository<OpeningModel, Long> {
}
