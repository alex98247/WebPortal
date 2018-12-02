package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.repository.CompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        Company company = companyRepository.findFirstById(id);
        return company;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        companyRepository.save(company);
        return ResponseEntity.ok().body(company);
    }

    @GetMapping
    public Collection<Company> getCompanies(){
        return companyRepository.findAll();
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        companyRepository.save(company);
            return ResponseEntity.ok().body(company);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Company> deleteCompany(@PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        companyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}