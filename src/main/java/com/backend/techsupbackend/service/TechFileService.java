package com.backend.techsupbackend.service;

import com.backend.techsupbackend.model.TechFile;
import com.backend.techsupbackend.repository.TechFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechFileService {

    @Autowired
    private TechFileRepository techFileRepository;

    public List<TechFile> allFiles(){
        return techFileRepository.findAll();
    }

    public void addFile(TechFile file){
        techFileRepository.save(file);
    }

    public void deleteFile(TechFile file){
        techFileRepository.delete(file);
    }

    public List<TechFile> findFilesForUserType(int userType){
        return techFileRepository.findByUserType(userType);
    }

    public TechFile findFromCode(String code){
        return techFileRepository.findById(code).orElseThrow();
    }
}
