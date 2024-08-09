package com.backend.techsupbackend.repository;

import com.backend.techsupbackend.model.Department;
import com.backend.techsupbackend.model.Directorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    public Department findByName(String name);
}
