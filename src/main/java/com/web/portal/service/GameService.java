package com.web.portal.service;

import com.web.portal.models.Game;
import com.web.portal.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GameService implements IGameService{
        @Autowired
        private GameRepository repository;

        public List<Game> findAll() {
                List<Game> games = (List<Game>) repository.findAll();

                return games;
        }

}
