package com.web.portal.controllers;

import com.web.portal.models.Game;
import com.web.portal.models.Pager;
import com.web.portal.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10};

    private GameRepository gameRepository;


    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
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

    @GetMapping
    public Collection<Game> getGames() {
        return gameRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) throws URISyntaxException {
        gameRepository.save(game);
        return ResponseEntity.created(new URI("/api/game/" + game.getId()))
                .body(game);
    }

    @PutMapping
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        gameRepository.save(game);
        return ResponseEntity.ok().body(game);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Game> deleteGame(@PathVariable("gameId") String gameId) {
        long id = Long.parseLong(gameId);
        gameRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
