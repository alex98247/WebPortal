package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.models.Game;
import com.web.portal.repository.CompanyRepository;
import com.web.portal.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<Game> games = gameRepository.findAll(PageRequest.of(0, 5)).getContent();
        model.addAttribute("games", games);
        return "homePage";
    }

    @GetMapping("/game/create")
    public String createGame(Model model) {
        Iterable<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "addGame";
    }

    @PostMapping("/game/create")
    public String createGame(@ModelAttribute Game game, @RequestParam String comp) {
        Company company = companyRepository.findFirstById(Long.parseLong(comp));
        game.setCompany(company);
        gameRepository.save(game);
        return "homePage";
    }

    @GetMapping("/game/update/{gameId}")
    public String updateGame(Model model, @PathVariable("gameId") String gameId) {
        long id = Long.parseLong(gameId);
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            model.addAttribute("game", game.get());
            return "editGame";
        }
        return "error";
    }

    @PostMapping("/game/update")
    public RedirectView updateGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return new RedirectView("/");
    }

    @GetMapping("/game/delete/{gameId}")
    public String updateGame(@PathVariable("gameId") String gameId) {
        long id = Long.parseLong(gameId);
        gameRepository.deleteById(id);
        return "homePage";
    }
}
