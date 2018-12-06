package com.web.portal.repository;

import com.web.portal.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

    @Override
    List<Game> findAll();

    Page<Game> findAllByName(String name, PageRequest pageRequest);

    List<Game> findByCompanyId(long companyId);
}
