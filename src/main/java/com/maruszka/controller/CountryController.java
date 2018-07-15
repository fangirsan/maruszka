package com.maruszka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maruszka.entity.Country;
import com.maruszka.errorResponse.CountryErrorResponse;
import com.maruszka.errorResponse.CountryNotFoundException;
import com.maruszka.services.CountryService;

/*
 *  https://kodowaniedoszuflady.pl/java/obsluga-wyjatkow-w-spring-mvc/
 */

@Controller
@RequestMapping("/country")
public class CountryController {

	// need to inject country service
	@Autowired
	CountryService countryService;
	
	// add an initBinder... to convert trim input strings
	// remove leading and trailing whitespaces
	// resolve issue for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimerEditor);
	}
	
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
	public String saveCountry(@Valid @ModelAttribute("country") Country theCountry,	BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()) {
			return "country-form";
		}
		else {
			try {
				countryService.saveCountry(theCountry);
				
				return "redirect:/country/list";
				
			} catch (DataIntegrityViolationException e) {
				
				theBindingResult.rejectValue("countryName", "countryName.error", "Invalid country name");
				
		        return "country-form";
			}
		}
	}
	
	@GetMapping("/showCountryUpdateForm")
	public String showCountryUpdateForm(@RequestParam("countryId") int theId,
			Model theModel) {
		
		Country theCountry = countryService.getCountry(theId);
		
		theModel.addAttribute("country", theCountry);
		
		List<Integer> theCountriesId = countryService.getCoutriesId();
		
		for (Integer id : theCountriesId) {
			System.out.println("\nID: " + id);
		}
		
		if ( (!theCountriesId.contains(new Integer(theId)) ) || ( theId < 0 ) ) {
			throw new CountryNotFoundException("Country id not found - " + theId);
		}
		else {
			return "country-form";	
		}
	}
	
	@GetMapping("/delete")
	public String deleteCountry(@RequestParam("countryId") int theId ) {
		
		countryService.deleteCountry(theId);
		
		return "redirect:/country/list";
	}
	
	// Add an exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<CountryErrorResponse> handleException(CountryNotFoundException exc) {
	
		// create a CountryErrorResponse
		CountryErrorResponse error = new CountryErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// add another exception handler... to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<CountryErrorResponse> handleException(Exception exc) {
		
		// create a CountryErrorResponse
		CountryErrorResponse error = new CountryErrorResponse();
		
		error.setStatus(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
