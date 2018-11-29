package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.models.Game;
import com.web.portal.models.Pager;
import com.web.portal.repository.CompanyRepository;
import com.web.portal.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10};

    private GameRepository gameRepository;

    private CompanyRepository companyRepository;

    public GameController(GameRepository gameRepository, CompanyRepository companyRepository) {
        this.gameRepository = gameRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
                        @RequestParam("page") Optional<Integer> page) {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Game> gameList = gameRepository.findAll(new PageRequest(evalPage, evalPageSize));

        Pager pager = new Pager(gameList.getTotalPages(), gameList.getNumber(),BUTTONS_TO_SHOW);

        model.addAttribute("gameList", gameList);
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add page sizes
        model.addAttribute("pageSizes", PAGE_SIZES);
        // add pager
        model.addAttribute("pager", pager);

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
        return "redirect:/";
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
        return "redirect:/";
    }
}
