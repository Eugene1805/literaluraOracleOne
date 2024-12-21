package com.example.ProjectAlura.model;

import com.example.ProjectAlura.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(List<BookDTO> results) {
}
