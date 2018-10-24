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

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "homePage";
    }

    @RequestMapping("/company")
    public String company(@RequestParam("companyId") int id, Model model) {
        Company company = companyRepository.findFirstById(id);
        model.addAttribute("company", company);
        return "company";
    }
}
