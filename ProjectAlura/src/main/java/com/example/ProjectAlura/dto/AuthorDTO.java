package com.example.ProjectAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDTO(Integer birth_year, //"birth_year": <number or null>,
                        Integer death_year, //"death_year": <number or null>,
                        String name) {
}
