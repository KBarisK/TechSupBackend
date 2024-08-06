package com.backend.techsupbackend.repository;
import com.backend.techsupbackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {
    Optional<Admin> findByUsername(String username);

}
