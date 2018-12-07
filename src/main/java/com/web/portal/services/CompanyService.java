package com.web.portal.services;

import com.web.portal.models.Company;

import java.util.List;

public interface CompanyService {
    void saveCompany(Company company);
    void deleteById(long id);
    List<Company> findAll();
    Company findFirstById(long id);
}
