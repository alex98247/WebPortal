package com.web.portal.controllers;

import com.web.portal.models.Game;
import com.web.portal.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "homePage";
    }
}
