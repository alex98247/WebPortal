package com.web.portal.controllers;

import com.web.portal.models.Game;
import com.web.portal.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<Game> games = gameRepository.findAll(PageRequest.of(0, 3)).getContent();
        model.addAttribute("games", games);
        return "homePage";
    }

    @PostMapping("/game/create")
    public String createGame(@ModelAttribute Model model) {

        return "homePage";
    }

    @PostMapping("/game/update")
    public String updateGame(@ModelAttribute Model model) {

        return "homePage";
    }

    @PostMapping("/game/delete/{gameId}")
    public String updateGame(@PathVariable("gameId") String gameId) {

        return "homePage";
    }
}
