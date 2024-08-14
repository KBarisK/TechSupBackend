package com.backend.techsupbackend.service;

import com.backend.techsupbackend.model.Directorate;
import com.backend.techsupbackend.model.TechFile;
import com.backend.techsupbackend.repository.DirectorateRepository;
import com.backend.techsupbackend.repository.TechFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorateService {

    @Autowired
    DirectorateRepository directorateRepository;

    @Autowired
    TechFileRepository techFileRepository;

    public void addDirectorate(Directorate directorate){
        directorateRepository.save(directorate);
    }

    public void deleteDirectorate(String name){
        String[] strings = name.split("%20");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            stringBuilder.append(strings[i]).append(" ");
        }
        stringBuilder.append(strings[strings.length - 1]);
        name = stringBuilder.toString();
        Directorate directorate =directorateRepository.findByName(name);
        directorateRepository.delete(directorate);
        List<TechFile> fileList = techFileRepository.findAll();
        for (int i = 0; i < fileList.size(); i++) {
            TechFile file = fileList.get(i);
            List<String> directorateList = file.getDirectorateList();
            System.out.println(directorateList);
            for (int j = 0; j < directorateList.size() ; j++) {
                String directorate_name = directorateList.get(i);
                if (directorate_name.equals(name)){
                    directorateList.remove(i);
                }
            }
            file.setDirectorateList(directorateList);
            techFileRepository.save(file);
        }
    }

    public List<Directorate> getAll(){
        return directorateRepository.findAll();
    }

    public Directorate getDirectorate(int id){
        return directorateRepository.findById(id).orElseThrow();
    }

    public Directorate getDirectorate(String name){
        return directorateRepository.findByName(name);
    }
}
