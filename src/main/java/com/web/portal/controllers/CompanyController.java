package com.web.portal.controllers;

import com.web.portal.models.Company;
import com.web.portal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping("/company")
    public String getCompany(@RequestParam("companyId") int id, Model model) {
        Company company = companyRepository.findFirstById(id);
        model.addAttribute("company", company);
        return "company";
    }

    @PostMapping("/company")
    public String postCompany(@RequestParam("companyId") int id, Model model) {
        Company company = companyRepository.findFirstById(id);
        model.addAttribute("company", company);
        return "company";
    }
}
