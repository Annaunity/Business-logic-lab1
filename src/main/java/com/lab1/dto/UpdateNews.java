package com.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNews {
    private String newsDescription;
    private String newText;
    List<Long> tags;
}
