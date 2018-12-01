package com.web.portal.controllers;

import com.web.portal.models.Game;
import com.web.portal.models.Pager;
import com.web.portal.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameRepository gameRepository;


    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public Pager index(@RequestParam("size") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> pageNumber) {

        int pageId = (pageNumber.isPresent()) ? pageNumber.get() : 0;
        int size = (pageSize.isPresent()) ? pageSize.get() : 5;

        Page<Game> page = gameRepository.findAll(new PageRequest(pageId, size));

        boolean hasPreviousPage = pageId != 0;
        boolean hasNextPage = page.getTotalPages() != pageId;

        Pager pager = new Pager(page.getContent(), hasPreviousPage, pageId, hasNextPage, size);

        return pager;
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
