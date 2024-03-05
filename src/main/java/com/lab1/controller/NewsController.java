package com.lab1.controller;


import com.lab1.dto.NewNews;
import com.lab1.dto.UpdateNews;
import com.lab1.model.News;
import com.lab1.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {
    private final NewsService newsService;
    private final Logger logger;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
        this.logger = LoggerFactory.getLogger(NewsController.class);
    }

    @PostMapping("/admin/news")
    public News addNewNews(@Valid @RequestBody NewNews news) {
        logger.info("Request to create a new news");
        return newsService.saveNews(news);

    }

    @GetMapping("/news")
    public List<News> getAll() {
        logger.info("Request to getting all news");
        return newsService.allNews();
    }


    @GetMapping("/news/{id}")
    public News getOne(@PathVariable("id") long id) {
        logger.info("Request to getting news with id: " + id);
        return newsService.findById(id);
    }

    @PatchMapping("/admin/news/{id}")
    public News updateOne(@PathVariable("id") long id, @RequestBody UpdateNews news) {
        logger.info("Request to updating news with id: " + id);
        return newsService.updateOne(id, news);
    }
}
