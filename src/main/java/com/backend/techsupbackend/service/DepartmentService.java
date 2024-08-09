package com.backend.techsupbackend.service;

import com.backend.techsupbackend.model.Department;
import com.backend.techsupbackend.model.Directorate;
import com.backend.techsupbackend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(String name) {
        Department department = departmentRepository.findByName(name);
        departmentRepository.delete(department);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(int id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    public Department getDepartment(String name) {
        return departmentRepository.findByName(name);
    }
}
