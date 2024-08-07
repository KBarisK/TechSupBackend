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
        String type = file.getType();
        int amount = findFromType(type).size();
        if (type.equals("Süreç")){
            file.setCode("SB" + amount);
        }else {
            file.setCode("BB" + amount);
        }
        techFileRepository.save(file);
    }

    public TechFile findByName(String name){
        return techFileRepository.findByName(name);
    }

    public void deleteFile(TechFile file){
        techFileRepository.delete(file);
    }

    public List<TechFile> directorateFiles(String name){
        return techFileRepository.findAllBydirectorateList_Name(name);
    }
    public List<TechFile> directorateFiles(Integer id){
        return techFileRepository.findAllBydirectorateList_Id(id);
    }

    public List<TechFile> findFromType(String type){
     return techFileRepository.findAllByType(type);
    }

    public TechFile findFromCode(String code){
        return techFileRepository.findById(code).orElseThrow();
    }
}
