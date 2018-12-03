package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.repository.CompanyRepository;
import com.web.portal.repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private CompanyRepository companyRepository;
    private GameRepository gameRepository;

    public CompanyController(CompanyRepository companyRepository, GameRepository gameRepository) {
        this.companyRepository = companyRepository;
        this.gameRepository = gameRepository;
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
    public ResponseEntity deleteCompany(@PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        boolean canDelete = gameRepository.findByCompanyId(id).isEmpty();
        if(canDelete) {
            companyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}