package com.lab1.controller;

import com.lab1.model.Feedback;
import com.lab1.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/news/{id}/feedback?username={username}")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final Logger logger;

    public FeedbackController(FeedbackService reviewService) {
        this.feedbackService = reviewService;
        this.logger = LoggerFactory.getLogger(FeedbackController.class);
    }

    @PostMapping()
    public Map<String, String> addNewReview(@PathVariable("id") long id, @PathVariable("username") String username, @Valid @RequestBody Feedback feedback) {
        logger.info("Request to create a new feedback on news with id: " + id);
        var createdFeedback = feedbackService.saveFeedback(username, id, feedback);

        Map<String, String> data = new HashMap<>();
        data.put("id", String.valueOf(createdFeedback.getId()));
        data.put("author", createdFeedback.getAuthor());
        data.put("text", createdFeedback.getText());
        data.put("timestamp", String.valueOf(createdFeedback.getTimestamp()));
        data.put("rating", String.valueOf(createdFeedback.getRating()));

        return data;
    }
}
