package com.backend.techsupbackend.controller;

import com.backend.techsupbackend.model.TechFile;
import com.backend.techsupbackend.service.TechFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("/tg/api")
@RestController
public class TechFileController {
    @Autowired
    public TechFileService techFileService;


    @DeleteMapping(value = "/admin/file/{code}")
    public ResponseEntity deleteFile(@PathVariable String code) {
        TechFile file = techFileService.findFromCode(code);
        String name = file.getName() + ".pdf";
        String rootPath = new FileSystemResource("").getFile().getAbsolutePath();
        try {
            Files.deleteIfExists(Paths.get(rootPath + "\\public\\" + name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        techFileService.deleteFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/file")
    public ResponseEntity createNewFile(@RequestBody TechFile techFile) {
        techFileService.addFile(techFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/files")
    public List<TechFile> getAllFiles() {
        return techFileService.allFiles();
    }

    @GetMapping(value = "/file/{code}")
    public TechFile getFileByCode(@PathVariable String code) {
        return techFileService.findFromCode(code);
    }

    @GetMapping(value = "/file/directorate/{id}")
    public List<TechFile> getFilesByDirectorate(@PathVariable int id){
        return techFileService.directorateFiles(id);
    }

    @PostMapping(value = "/admin/upload")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String rootPath = new FileSystemResource("").getFile().getAbsolutePath();
        try {
            file.transferTo(new File(rootPath + "\\public\\" + fileName));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}