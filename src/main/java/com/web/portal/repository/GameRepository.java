package com.web.portal.repository;

import com.web.portal.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface GameRepository extends CrudRepository<Game, Long> { //pageable crud rep
    Page<Game> findAll(Pageable pageable);
}
