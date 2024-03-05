package com.lab1.repository;

import com.lab1.model.NewsTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<NewsTag, Long> {
    NewsTag findByTagName(String name);
}
