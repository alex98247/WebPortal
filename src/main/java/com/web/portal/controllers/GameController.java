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
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/game/create")
    public String createGameGet(Model model) {
        List<Game> games = gameRepository.findAll(PageRequest.of(0, 3)).getContent();
        model.addAttribute("games", games);
        return "homePage";
    }

    @PostMapping("/game/create")
    public String createGamePost(@ModelAttribute Model model) {

        return "homePage";
    }

    @GetMapping("/game/update/{gameId}")
    public String updateGameGet(Model model, @PathVariable("gameId") String gameId) {
        long id = Long.parseLong(gameId);
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()) {
            model.addAttribute("game", game.get());
            return "addGame";
        }
        return "error";
    }

    @PostMapping("/game/update")
    public RedirectView updateGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return new RedirectView("/");
    }

    @PostMapping("/game/delete/{gameId}")
    public String updateGame(@PathVariable("gameId") String gameId) {

        return "homePage";
    }
}
