package com.backend.techsupbackend.service;

import com.backend.techsupbackend.model.Directorate;
import com.backend.techsupbackend.model.TechFile;
import com.backend.techsupbackend.repository.DirectorateRepository;
import com.backend.techsupbackend.repository.TechFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechFileService {

    @Autowired
    private TechFileRepository techFileRepository;
    @Autowired
    private DirectorateRepository directorateService;

    public List<TechFile> allFiles(){
        return techFileRepository.findAll();
    }

    public void addFile(TechFile file){
        String type = file.getType();
        int amount;
        TechFile techFile =  findFromType(type).getLast();
        if (techFile == null) {
            amount = 0;
        }
        else {
            amount = Integer.parseInt(findFromType(type).getLast().getCode().substring(3)) + 1;
        }
        if (type.equals("Süreç")){
            file.setCode("SB" + amount);
        }else {
            file.setCode("BB" + amount);
        }
        List<String> directorateList = file.getDirectorateList();
        for (int i = 0; i < directorateList.size(); i++) {
            Directorate directorate = directorateService.findByName(directorateList.get(i));
            directorate.getFileList().add(file.getName());
        }
        techFileRepository.save(file);
    }

    public TechFile findByName(String name){
        return techFileRepository.findByName(name);
    }

    public void deleteFile(TechFile file){
        techFileRepository.delete(file);
    }

    public List<TechFile> findFromType(String type){
     return techFileRepository.findAllByType(type);
    }

    public TechFile findFromCode(String code){
        return techFileRepository.findById(code).orElseThrow();
    }
}
