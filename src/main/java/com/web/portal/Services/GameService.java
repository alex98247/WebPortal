package com.web.portal.Services;

import com.web.portal.models.Game;
import com.web.portal.models.Pager;

import java.util.Optional;

public interface GameService {
    Pager findSorted(Optional<Integer> pageSize, Optional<Integer> pageNumber, Optional<String> sort, Optional<String> name);
    void saveGame(Game game);
    void deleteById(long id);
    boolean canDeleteGame(long id);
}
