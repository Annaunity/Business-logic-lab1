package com.lab1.controller;

import com.lab1.model.NewsTag;
import com.lab1.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TagController {
    private final TagService tagService;
    private final Logger logger;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
        this.logger = LoggerFactory.getLogger(TagController.class);
    }

    @PostMapping("/admin/tag")
    public Map<String, String> addNewTag(@Valid @RequestBody NewsTag tag) {
        logger.info("request to create a new tag");
        var createdTag = tagService.saveTag(tag);
        if (createdTag == null) {
            return Map.of("error", "tag already exist");
        }

        Map<String, String> data = new HashMap<>();
        data.put("id", String.valueOf(tag.getId()));
        data.put("name", tag.getTagName());

        return data;
    }

    @GetMapping("/tag")
    public List<NewsTag> getAll() {
        logger.info("Request to getting all tags");
        return tagService.allTags();
    }

    @GetMapping("/tag/{id}")
    public NewsTag getOne(@PathVariable("id") long id) {
        logger.info("Request to getting tag with id: " + id);
        return tagService.getTag(id);
    }
}
