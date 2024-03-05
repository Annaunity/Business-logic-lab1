package com.lab1.service;

import com.lab1.model.NewsTag;
import com.lab1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<NewsTag> allTags() {
        return tagRepository.findAll();
    }

    public NewsTag getTag(long id) {
        return tagRepository.findById(id).orElse(null);
    }

    public NewsTag saveTag(NewsTag tag) {
        var tagFromDB = tagRepository.findByTagName(tag.getTagName());
        if (tagFromDB != null) {
            return null;
        }

        return tagRepository.save(tag);
    }
}
