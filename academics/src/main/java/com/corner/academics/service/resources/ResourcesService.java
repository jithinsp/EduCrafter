package com.corner.academics.service.resources;

import com.corner.academics.dto.ResourcesRequest;
import com.corner.academics.entity.Resources;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface ResourcesService {
    void init();

    void store(ResourcesRequest resourcesRequest);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    List<Resources> loadAllResources();
}
