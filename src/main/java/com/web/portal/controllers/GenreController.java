package com.web.portal.controllers;

import com.web.portal.models.Genre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @GetMapping
    public Genre[] getGenre(){
        return Genre.values();
    }
}
