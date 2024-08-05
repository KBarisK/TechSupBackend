package com.backend.techsupbackend.controller;

import com.backend.techsupbackend.model.TechFile;
import com.backend.techsupbackend.service.TechFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class TechFileController {
    @Autowired
    public TechFileService techFileService;
    @DeleteMapping(value = "/test/{code}")
    public ResponseEntity deleteFile(@PathVariable String code){
        TechFile file = techFileService.findFromCode(code);
        techFileService.deleteFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
