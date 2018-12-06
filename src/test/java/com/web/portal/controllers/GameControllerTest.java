package com.web.portal.controllers;

import com.web.portal.Services.GameService;
import com.web.portal.models.Company;
import com.web.portal.models.Game;
import com.web.portal.models.Genre;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameControllerTest {
    private GameService gameService;
    private GameController gameController;
    private Game game;

    @Before
    public void setMock() {
        Model model = mock(Model.class);
        gameService = mock(GameService.class);
        gameController = new GameController(gameService);
        configureGame();
    }

    private void configureGame() {
        this.game = new Game();

        Company company = new Company();
        company.setCountry("Russia");
        company.setId(66L);
        company.setName("Putnikov,Mironov and Fortune");

        game.setId(42L);
        game.setCompany(company);
        game.setGenre(Genre.SURVIVAL);
        game.setName("GamePortal");
        game.setYear(2018);
        game.setDescription("Spring and other perfect ways to shoot yourself in the foot");
    }

    @Test
    public void addGameTest() {
        ResponseEntity result = gameController.createGame(game);
        verify(gameService).saveGame(game);
        assertEquals(ResponseEntity.ok().body(game), result);
    }

    @Test
    public void updateGameTest() {
        ResponseEntity result = gameController.updateGame(game);
        verify(gameService).saveGame(game);
        assertEquals(ResponseEntity.ok().body(game), result);
    }

    @Test
    public void removeGameTest() {
        ResponseEntity result = gameController.deleteGame(game.getId().toString());
        verify(gameService).deleteById(game.getId());
        assertEquals(ResponseEntity.ok().build(), result);
    }
}