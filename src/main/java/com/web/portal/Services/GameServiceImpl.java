package com.web.portal.Services;

import com.web.portal.models.Game;
import com.web.portal.models.Pager;
import com.web.portal.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Pager findSorted(Optional<Integer> pageSize, Optional<Integer> pageNumber, Optional<String> sort, Optional<String> name) {

        int pageId = (pageNumber.isPresent()) ? pageNumber.get() : 0;
        int size = (pageSize.isPresent()) ? pageSize.get() : 5;
        String sortParam = (sort.isPresent()) ? sort.get() : "id";
        Page<Game> page;

        if (name.isPresent())
            page = gameRepository.findAllByName(name.get(), PageRequest.of(pageId, size, Sort.by(sortParam)));
        else
            page = gameRepository.findAll(PageRequest.of(pageId, size, Sort.by(sortParam)));

        boolean hasPreviousPage = pageId != 0;
        boolean hasNextPage = page.getTotalPages() - 1 > pageId;

        Pager pager = new Pager(page.getContent(), hasPreviousPage, pageId, hasNextPage, size, page.getTotalPages());

        return pager;
    }


    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void deleteById(long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public boolean canDeleteGame(long id) {
        return gameRepository.findByCompanyId(id).isEmpty();
    }
}
