package com.backend.techsupbackend.model;

import com.backend.techsupbackend.service.TechFileService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class TechFile {
    @Id
    private String code;
    private String name;
    private String type;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> users;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> departments;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> directorateList;

    protected TechFile(){}

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public TechFile(String code, String name, String type, List<String> users, List<String> departments, List<String> directorateList) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.users = users;
        this.departments = departments;
        this.directorateList = directorateList;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getDirectorateList() {
        return directorateList;
    }

    public void setDirectorateList(List<String> directorateList) {
        this.directorateList = directorateList;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
