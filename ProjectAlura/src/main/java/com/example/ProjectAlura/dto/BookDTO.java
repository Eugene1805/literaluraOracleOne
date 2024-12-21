package com.example.ProjectAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        Long id, //"id": <number of Project Gutenberg ID>,
        String title, // "title": <string>,
        List<String> subjects, //"subjects": <array of strings>,
        List<AuthorDTO> authors, //"authors": <array of Persons>,
        List<String> bookshelves, //"bookshelves": <array of strings>,
        List<String> languages, //"languages": <array of strings>,
        String media_type, //"media_type": <string>,
        Integer download_count//"download_count": <number>
) {
}
