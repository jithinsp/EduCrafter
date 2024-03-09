package com.corner.academics.controller;

import com.corner.academics.dto.ResourcesRequest;
import com.corner.academics.entity.Resources;
import com.corner.academics.exception.StorageException;
import com.corner.academics.exception.StorageFileNotFoundException;
import com.corner.academics.service.resources.ResourcesService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
@RequestMapping("academics/resources/")
public class ResourcesController {
    //constructor injection
    private final ResourcesService resourcesService;

    @Autowired
    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @GetMapping("/")
    public ResponseEntity<?> listUploadedFiles() throws IOException {
        try {
            List<String> fileUris = resourcesService.loadAll()
                    .map(path -> MvcUriComponentsBuilder.fromMethodName(ResourcesController.class,
                            "serveFile", path.getFileName().toString()).build().toUri().toString())
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(fileUris);
        } catch (StorageException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO error occurred: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("listAll")
    public ResponseEntity<?> listAll() throws IOException {
        try {
            List<Resources> resources = resourcesService.loadAllResources();
            return ResponseEntity.ok().body(resources);
        } catch (StorageException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO error occurred: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable String filename, HttpServletResponse response) {
        try {
            Resource resource = resourcesService.loadAsResource(filename);
//            if (resource == null)
//                return ResponseEntity.notFound().build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file,
                                              @RequestParam(value = "subject", required = false) UUID subjectId,
                                              @RequestParam(value = "classes", required = false)  UUID classesId) {
        try {
            ResourcesRequest resourcesRequest = new ResourcesRequest(subjectId, classesId, file);
            resourcesService.store(resourcesRequest);
            return ResponseEntity.status(HttpStatus.OK).body("uploaded: " + resourcesRequest.getFile().getOriginalFilename());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (StorageException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO error occurred: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}