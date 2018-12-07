package com.web.portal.services;

import com.web.portal.models.Company;
import com.web.portal.models.Game;
import com.web.portal.models.Genre;
import com.web.portal.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameServiceTest {
    private GameRepository gameRepository;
    private GameService gameService;
    private Game game;

    @Before
    public void setMock() {
        Model model = mock(Model.class);
        gameRepository = mock(GameRepository.class);
        gameService = new GameServiceImpl(gameRepository);
        configureGame();
    }

    private void configureGame() {
        this.game = new Game();

        Company company = new Company();
        company.setCountry("USA");
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
        gameService.saveGame(game);
        verify(gameRepository).save(game);
    }

    @Test
    public void removeGameTest() {
        gameService.deleteById(game.getId());
        verify(gameRepository).deleteById(game.getId());
    }

}
