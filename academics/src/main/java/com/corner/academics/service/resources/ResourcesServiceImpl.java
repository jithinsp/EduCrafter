package com.corner.academics.service.resources;

import com.corner.academics.config.StorageProperties;
import com.corner.academics.dto.ClassesRequest;
import com.corner.academics.dto.ResourcesRequest;
import com.corner.academics.entity.Resources;
import com.corner.academics.exception.StorageException;
import com.corner.academics.exception.StorageFileNotFoundException;
import com.corner.academics.repository.ClassesRepository;
import com.corner.academics.repository.ResourcesRepository;
import com.corner.academics.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResourcesServiceImpl implements ResourcesService{

    @Autowired
    ResourcesRepository resourcesRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    ClassesRepository classesRepository;

    private final Path rootLocation;

    @Autowired
    public ResourcesServiceImpl(StorageProperties properties) {
        if(properties.getLocation().trim().length() == 0){
        throw new StorageException("File upload location can not be Empty.");
    }

		this.rootLocation = Paths.get(properties.getLocation());
}

    @Override
    public void store(ResourcesRequest resourcesRequest) {
        try {
            if (resourcesRequest.getFile().isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(resourcesRequest.getFile().getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = resourcesRequest.getFile().getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                Resources resource = new Resources();
                resource.setFilepath(resourcesRequest.getFile().getOriginalFilename());
                if(resourcesRequest.getSubjectId()!=null){
                    resource.setSubject(subjectRepository
                            .findById(resourcesRequest
                                    .getSubjectId())
                            .orElseThrow(()->new EntityNotFoundException("Not found")));
                }
                if(resourcesRequest.getClassesId()!=null) {
                    resource.setClasses(classesRepository
                            .findById(resourcesRequest
                                    .getClassesId())
                            .orElseThrow(() -> new EntityNotFoundException("Not found")));
                }
                resourcesRepository.save(resource);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public List<Resources> loadAllResources() {
        return resourcesRepository.findAll();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}