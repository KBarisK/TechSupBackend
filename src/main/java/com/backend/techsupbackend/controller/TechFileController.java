package com.backend.techsupbackend.controller;

import com.backend.techsupbackend.model.Directorate;
import com.backend.techsupbackend.model.TechFile;
import com.backend.techsupbackend.service.DirectorateService;
import com.backend.techsupbackend.service.TechFileService;
import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
        String rootPath = new FileSystemResource("").getFile().getAbsolutePath();
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(rootPath + "\\public"));
            for (Path tempPath : stream) {
                String fileName = tempPath.getFileName().toString();
                String[] temp = fileName.split("\\."); //it is okay to do because we change file names like BB1 BB0 so there is no other .
                if (temp[0].equals(code)){
                    String name = file.getCode() + "." + temp[1];
                    try {
                        Files.deleteIfExists(Paths.get(rootPath + "\\public\\" + name));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        techFileService.deleteFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/file")
    public ResponseEntity createNewFile(@RequestBody TechFile techFile) {
        techFileService.addFile(techFile);
        String code = techFileService.findByName(techFile.getName()).getCode();
        return ResponseEntity.ok(code);
    }

    @GetMapping(value = "/files")
    public List<TechFile> getAllFiles() {
        return techFileService.allFiles();
    }

    @GetMapping(value = "/file/{code}")
    public TechFile getFileByCode(@PathVariable String code) {
        return techFileService.findFromCode(code);
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