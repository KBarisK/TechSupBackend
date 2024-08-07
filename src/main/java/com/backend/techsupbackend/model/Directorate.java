package com.backend.techsupbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Directorate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ElementCollection
    private List<String> fileList;

    public Directorate(int id, String name, List<String> fileList) {
        this.id = id;
        this.name = name;
        this.fileList = fileList;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
