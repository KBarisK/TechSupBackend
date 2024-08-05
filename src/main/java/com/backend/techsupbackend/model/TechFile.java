package com.backend.techsupbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TechFile {
    @Id
    private String code;
    private String name;
    private String type;
    private int userType;

    protected TechFile(){}

    public TechFile(String code, String name, String type,int userType) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.userType = userType;
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
