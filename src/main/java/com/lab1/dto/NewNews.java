package com.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewNews {
    @NotBlank
    @NotEmpty
    private String newsTitle;

    @NotBlank
    @NotEmpty
    private String newsDescription;

    @NotBlank
    @NotEmpty
    private String newText;

    List<Long> tags = new ArrayList<>();
}

