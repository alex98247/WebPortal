package com.web.portal.repository;

import com.web.portal.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
    Company findFirstById(long id);

}
