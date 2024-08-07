package com.backend.techsupbackend.controller;

import com.backend.techsupbackend.model.Directorate;
import com.backend.techsupbackend.service.DirectorateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectorateController {

    @Autowired
    DirectorateService directorateService;


    @PostMapping(value = "/admin/directorate")
    public ResponseEntity addDirectorate(@RequestBody Directorate directorate){
        directorateService.addDirectorate(directorate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/admin/directorate")
    public ResponseEntity deleteDirectorate(@RequestBody Directorate directorate){
        directorateService.deleteDirectorate(directorate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/directorate")
    public List<Directorate> getAll(){
        return directorateService.getAll();
    }

    @GetMapping(value = "/directorate/{id}")
    public Directorate getDirectorate(@PathVariable int id){
        return directorateService.getDirectorate(id);
    }
}
