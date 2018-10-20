package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.models.Game;
import com.web.portal.repository.CompanyRepository;
import com.web.portal.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.StreamSupport;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CompanyRepository companyRepositorypository;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "index";
    }

    @RequestMapping("/company")
    public String company(@RequestParam("companyId") int id, Model model) {
        Iterable<Company> games = companyRepositorypository.findAll();
        Company company = StreamSupport.stream(games.spliterator(), false).filter(x -> x.getId() == id).findFirst().get();
        model.addAttribute("company", company);
        return "company";
    }
}
