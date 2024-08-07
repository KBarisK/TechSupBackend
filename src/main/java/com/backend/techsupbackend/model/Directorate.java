package com.backend.techsupbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Directorate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "directorateList")
    private List<TechFile> fileList;

    public Directorate(int id, String name, List<TechFile> fileList) {
        this.id = id;
        this.name = name;
        this.fileList = fileList;
    }
    public Directorate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TechFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<TechFile> fileList) {
        this.fileList = fileList;
    }
}
