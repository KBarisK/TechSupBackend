package com.backend.techsupbackend.service;

import com.backend.techsupbackend.model.Department;
import com.backend.techsupbackend.model.Directorate;
import com.backend.techsupbackend.model.TechFile;
import com.backend.techsupbackend.repository.DepartmentRepository;
import com.backend.techsupbackend.repository.TechFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TechFileRepository techFileRepository;

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(String name) {
        String[] strings = name.split("%20");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            stringBuilder.append(strings[i]).append(" ");
        }
        stringBuilder.append(strings[strings.length - 1]);
        name = stringBuilder.toString();
        Department department = departmentRepository.findByName(name);
        departmentRepository.delete(department);
        List<TechFile> fileList = techFileRepository.findAll();
        for (int i = 0; i < fileList.size(); i++) {
            TechFile file = fileList.get(i);
            List<String> departmentList = file.getDepartments();
            for (int j = 0; j < departmentList.size() ; j++) {
                String department_name = departmentList.get(i);
                if (department_name.equals(name)){
                    departmentList.remove(i);
                }
            }
            file.setDirectorateList(departmentList);
            techFileRepository.save(file);
        }
    }

    public void deleteDepartment(Integer id){
        Department department = departmentRepository.findById(id).orElseThrow();
        String name = department.getName();
        departmentRepository.deleteById(id);
        List<TechFile> fileList = techFileRepository.findAll();
        for (int i = 0; i < fileList.size(); i++) {
            TechFile file = fileList.get(i);
            List<String> departmentList = file.getDepartments();
            for (int j = 0; j < departmentList.size() ; j++) {
                String department_name = departmentList.get(i);
                if (department_name.equals(name)){
                    departmentList.remove(i);
                }
            }
            file.setDirectorateList(departmentList);
            techFileRepository.save(file);
        }
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
