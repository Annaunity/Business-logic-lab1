package com.lab1.service;

import com.lab1.dto.NewNews;
import com.lab1.dto.UpdateNews;
import com.lab1.model.News;
import com.lab1.model.NewsTag;
import com.lab1.repository.NewsRepository;
import com.lab1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    private final TagRepository tagRepository;
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(TagRepository tagRepository, NewsRepository newsRepository) {
        this.tagRepository = tagRepository;
        this.newsRepository = newsRepository;
    }

    public List<News> allNews() {
        return newsRepository.findAll();
    }

    public News findById(long id) {
        var News = newsRepository.findById(id);
        return News.orElse(null);
    }

    public News saveNews(NewNews newNews) {
        var newsFromDB = newsRepository.findByNewsTitle(newNews.getNewsTitle());
        if (newsFromDB != null) {
            return null;
        }

        if (newNews.getTags() == null) {
            newNews.setTags(new ArrayList<>());
        }

        var newsTags = checkTags(newNews.getTags());
        if (newsTags == null) {
            return null;
        }

        var news = new News();
        news.setNewsTitle(newNews.getNewsTitle());
        news.setNewsDescription(newNews.getNewsDescription());
        news.setTags(newsTags);

        if (news.getFeedbacks() == null) {
            news.setFeedbacks(new ArrayList<>());
        }

        return newsRepository.save(news);
    }

    public News updateOne(long id, UpdateNews updateNews) {
        var newsFromDB = newsRepository.findById(id).orElse(null);
        if (newsFromDB == null) {
            return null;
        }

        if (updateNews.getNewsDescription() != null) {
            newsFromDB.setNewsDescription(updateNews.getNewsDescription());
        }

        if (updateNews.getNewText() != null) {
            newsFromDB.setNewsText(updateNews.getNewText());
        }

        if (updateNews.getTags() != null) {
            var newsTags = checkTags(updateNews.getTags());
            if (newsTags == null) {
                return null;
            }
            newsFromDB.setTags(newsTags);
        }

        return newsRepository.save(newsFromDB);
    }

    private List<NewsTag> checkTags(List<Long> tags) {
        List<NewsTag> newsTags = new ArrayList<>();
        for (var tagId : tags) {
            var tagFromDB = tagRepository.findById(tagId).orElse(null);
            newsTags.add(tagFromDB);
            if (tagFromDB == null) {
                return null;
            }
        }
        return newsTags;
    }

}
