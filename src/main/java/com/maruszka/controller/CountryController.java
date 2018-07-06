package com.maruszka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maruszka.entity.Country;
import com.maruszka.services.CountryService;

@Controller
@RequestMapping("/country")
public class CountryController {

	// need to inject country service
	@Autowired
	CountryService countryService;
	
	@GetMapping("/list")
	public String listCountries(Model theModel) {

		List<Country> theCountries = countryService.getCountries();
		
		theModel.addAttribute("countries", theCountries); 
		
		return "countries-list";
	}
	
	@GetMapping("/showAddCountryForm")
	public String showAddCountryForm(Model theModel) {

		Country theCountry = new Country();
		
		theModel.addAttribute("country", theCountry);
		
		return "country-form";
	}
	
	@PostMapping("/saveCountry")
	public String saveCountry(@Valid @ModelAttribute("country") Country theCountry,
			BindingResult theBindingresult) {
		
		if (theBindingresult.hasErrors()) {
			return "country-form";
		} else {
			countryService.saveCountry(theCountry);
		}
		
		return "redirect:/country/list";
	}
	
	@GetMapping("/showCountryUpdateForm")
	public String showCountryUpdateForm(@RequestParam("countryId") int theId,
			Model theModel) {
		
		Country theCountry = countryService.getCountry(theId);
		
		theModel.addAttribute("country", theCountry);
		
		return "country-form";
	}
	
	@GetMapping("/delete")
	public String deleteCountry(@RequestParam("countryId") int theId ) {
		
		countryService.deleteCountry(theId);
		
		return "redirect:/country/list";
	}
}
