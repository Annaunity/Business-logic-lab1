package com.lab1.service;

import com.lab1.model.Feedback;
import com.lab1.repository.FeedbackRepository;
import com.lab1.repository.NewsRepository;
import com.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, NewsRepository newsRepository, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public Feedback saveFeedback(String username, long newsId, Feedback feedback) {
        var userFromDB = userRepository.findByUsername(username);
        if (userFromDB == null) {
            return null;
        }

        var newsFromDB = newsRepository.findById(newsId).orElse(null);
        if (newsFromDB == null) {
            return null;
        }

        feedback.setAuthor(username);
        feedback.setNewsId(newsFromDB.getId());
        return feedbackRepository.save(feedback);
    }

}
