package com.web.portal.Services;

import com.web.portal.models.Game;
import com.web.portal.models.PageAndSort;
import com.web.portal.models.Pager;
import com.web.portal.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Pager findSorted(Optional<Integer> pageSize, Optional<Integer> pageNumber) {

        int pageId = (pageNumber.isPresent()) ? pageNumber.get() : 0;
        int size = (pageSize.isPresent()) ? pageSize.get() : 5;

        PageAndSort pageAndSort = new PageAndSort("id", pageId, size, "");

        Pager pager = findSort(pageAndSort);

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

    @Override
    public Pager findSorted(PageAndSort pageAndSort) {
        return findSort(pageAndSort);
    }

    private Pager findSort(PageAndSort pageAndSort) {
        int pageId = (pageAndSort.getFind().length() > 0) ? pageAndSort.getCurrentPage() : 0;
        int size = pageAndSort.getPageSize();
        String sortParam = pageAndSort.getSort();
        String find = pageAndSort.getFind();

        Page<Game> page = (find.length() > 0) ? gameRepository.findByName(find, PageRequest.of(pageId, size, Sort.by(sortParam))) :
                gameRepository.findAll(PageRequest.of(pageId, size, Sort.by(sortParam)));

        boolean hasPreviousPage = pageId != 0;
        boolean hasNextPage = page.getTotalPages() - 1 > pageId;

        Pager pager = new Pager(page.getContent(), hasPreviousPage, hasNextPage, page.getTotalPages(), pageAndSort);

        return pager;
    }
}
