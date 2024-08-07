package com.backend.techsupbackend.repository;

import com.backend.techsupbackend.model.TechFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechFileRepository extends JpaRepository<TechFile,String> {
    public TechFile findByName(String name);

    public List<TechFile> findAllByType(String type);

}
