package com.backend.techsupbackend.repository;

import com.backend.techsupbackend.model.Directorate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorateRepository extends JpaRepository<Directorate,Integer> {
    public Directorate findByName(String name);

}
