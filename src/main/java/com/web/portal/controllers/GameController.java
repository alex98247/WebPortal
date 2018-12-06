package com.web.portal.controllers;

import com.web.portal.Services.GameService;
import com.web.portal.Services.GameServiceImpl;
import com.web.portal.models.Game;
import com.web.portal.models.Pager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Pager> index(@RequestParam("size") Optional<Integer> pageSize,
                                       @RequestParam("page") Optional<Integer> pageNumber,
                                       @RequestParam("sort") Optional<String> sort,
                                       @RequestParam("name") Optional<String> name) {

        Pager pager = gameService.findSorted(pageSize, pageNumber, sort, name);
        return ResponseEntity.ok().body(pager);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        gameService.saveGame(game);
        return ResponseEntity.ok().body(game);
    }

    @PutMapping
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        gameService.saveGame(game);
        return ResponseEntity.ok().body(game);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity deleteGame(@PathVariable("gameId") String gameId) {
        long id = Long.parseLong(gameId);
        gameService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
