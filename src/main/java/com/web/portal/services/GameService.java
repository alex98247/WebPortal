package com.web.portal.services;

import com.web.portal.models.Game;
import com.web.portal.models.PageAndSort;
import com.web.portal.models.Pager;

import java.util.Optional;

public interface GameService {
    Pager findSorted(Optional<Integer> pageSize, Optional<Integer> pageNumber);
    void saveGame(Game game);
    void deleteById(long id);
    boolean canDeleteGame(long id);
    Pager findSorted(PageAndSort pageAndSort);
}
