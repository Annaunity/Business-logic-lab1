package com.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    @NotBlank
    @NotEmpty
    private String newsTitle;

    @Column(name = "description", columnDefinition = "text")
    @NotBlank
    @NotEmpty
    private String newsDescription;

    @Column(name = "text", columnDefinition = "text")
    @NotBlank
    @NotEmpty
    private String newsText;

    @Column(name = "rating")
    private int rating;

    @Column(name = "date")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @ManyToMany
    List<NewsTag> tags = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "news_id")
    List<Feedback> feedbacks = new ArrayList<>();
}
