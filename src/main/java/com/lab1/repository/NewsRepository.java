package com.lab1.repository;

import com.lab1.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findByNewsTitle(String title);
}
