package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.models.Game;
import com.web.portal.models.Genre;
import com.web.portal.models.Pager;
import com.web.portal.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameControllerTest {
    private GameRepository gameRepository;
    private GameController gameController;
    private Game game;

    @Before
    public void setMock() {
        Model model = mock(Model.class);
        gameRepository = mock(GameRepository.class);
        gameController = new GameController(gameRepository);
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
        verify(gameRepository).save(game);
        assertEquals(ResponseEntity.ok().body(game), result);
    }

    @Test
    public void updateGameTest() {
        ResponseEntity result = gameController.updateGame(game);
        verify(gameRepository).save(game);
        assertEquals(ResponseEntity.ok().body(game), result);
    }

    @Test
    public void removeGameTest() {
        ResponseEntity result = gameController.deleteGame(game.getId().toString());
        verify(gameRepository).deleteById(game.getId());
        assertEquals(ResponseEntity.ok().build(), result);
    }
}