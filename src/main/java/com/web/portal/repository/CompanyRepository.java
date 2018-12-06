package com.web.portal.repository;

import com.web.portal.models.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

    Company findFirstById(long id);

    @Override
    List<Company> findAll();
}
