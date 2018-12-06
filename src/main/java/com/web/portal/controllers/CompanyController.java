package com.web.portal.controllers;

import com.web.portal.Services.CompanyService;
import com.web.portal.Services.GameService;
import com.web.portal.models.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private CompanyService companyService;
    private GameService gameService;

    public CompanyController(CompanyService companyService, GameService gameService) {
        this.companyService = companyService;
        this.gameService = gameService;
    }


    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        Company company = companyService.findFirstById(id);
        return company;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        companyService.saveCompany(company);
        return ResponseEntity.ok().body(company);
    }

    @GetMapping
    public Collection<Company> getCompanies(){
        return companyService.findAll();
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        companyService.saveCompany(company);
            return ResponseEntity.ok().body(company);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity deleteCompany(@PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        boolean canDelete = gameService.canDeleteGame(id);
        if(canDelete) {
            companyService.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}