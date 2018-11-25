package com.web.portal.repository;

import com.web.portal.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
    //Page<Game> findAll(Pageable pageable);
}
