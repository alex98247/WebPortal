package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping("/company/get/{companyId}")
    public String getCompany(Model model, @PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        Company company = companyRepository.findFirstById(id);
        model.addAttribute("company", company);
        return "company";
    }

    @PostMapping("/company/create")
    public String createCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "company";
    }

    @GetMapping("/company/create")
    public String createCompany() {
        return "addCompany";
    }

    @GetMapping("/company/update/{companyId}")
    public String updateCompanyGet(Model model, @PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()) {
            model.addAttribute("company", company.get());
            return "editCompany";
        }
        return "error";
    }

    @PostMapping("/company/update")
    public String updateCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "homePage";
    }


    @GetMapping("/company/delete/{companyId}")
    public String deleteCompany(@PathVariable("companyId") String companyId) {
        long id = Long.parseLong(companyId);
        companyRepository.deleteById(id);
        return "company";
    }
}