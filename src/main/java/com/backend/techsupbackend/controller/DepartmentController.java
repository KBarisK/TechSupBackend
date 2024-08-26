package com.backend.techsupbackend.controller;

import com.backend.techsupbackend.model.Department;
import com.backend.techsupbackend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tg/api")
@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping(value = "/admin/department")
    public ResponseEntity addDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/admin/department/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Integer id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/department")
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @GetMapping(value = "/department/{id}")
    public Department getDepartment(@PathVariable int id){
        return departmentService.getDepartment(id);
    }
}
