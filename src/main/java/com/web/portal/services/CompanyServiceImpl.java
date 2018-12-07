package com.web.portal.services;

import com.web.portal.models.Company;
import com.web.portal.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){

        this.companyRepository = companyRepository;
    }
    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteById(long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findFirstById(long id) {
        return companyRepository.findFirstById(id);
    }
}
