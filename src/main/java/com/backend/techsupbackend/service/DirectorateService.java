package com.backend.techsupbackend.service;

import com.backend.techsupbackend.model.Directorate;
import com.backend.techsupbackend.repository.DirectorateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorateService {

    @Autowired
    DirectorateRepository directorateRepository;

    public void addDirectorate(Directorate directorate){
        directorateRepository.save(directorate);
    }

    public void deleteDirectorate(String name){
        Directorate directorate =directorateRepository.findByName(name);
        directorateRepository.delete(directorate);
    }

    public List<Directorate> getAll(){
        return directorateRepository.findAll();
    }

    public Directorate getDirectorate(int id){
        return directorateRepository.findById(id).orElseThrow();
    }
}
