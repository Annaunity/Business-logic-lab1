package com.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "text", columnDefinition = "text")
    private String text;

    @Column(name = "date")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Column(name = "rating")
    @Min(value = 0)
    @Max(value = 10)
    private int rating;

    @JsonIgnore
    @Column(name = "news_id")
    long newsId;
}
