package com.backend.techsupbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TechFile {
    @Id
    private String code;
    private String name;
    private String type;
    @ElementCollection
    private List<String> users;
    
    @ManyToMany
    @JoinTable(
            name = "techFile_directorate",
            joinColumns = @JoinColumn(name = "techFile_code"),
            inverseJoinColumns = @JoinColumn(name = "directorate_id")
    ) private List<Directorate> directorateList;

    protected TechFile(){}

    public TechFile(String code, String name, String type, List<Directorate> directorateList) {
        this.code = code;
        this.name = name;
        this.type = type;
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
