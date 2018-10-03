package com.maruszka.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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

import com.maruszka.entity.MaltManufacturer;
import com.maruszka.errorResponse.CountryErrorResponse;
import com.maruszka.errorResponse.CountryNotFoundException;
import com.maruszka.services.MaltManufacturerService;

@Controller
@RequestMapping("/maltManufacturer")
public class MaltManufacturerController {

	@Autowired
	MaltManufacturerService maltManufacturerService;
	
	// add an initBinder... to convert trim input strings
	// remove leading and trailing whitespaces
	// resolve issue for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimerEditor);
	}
	
	@GetMapping("/list")
	public String listMaltManufacturer(Model theModel) {
		
		List<MaltManufacturer> theMaltManufacturers = maltManufacturerService.getMaltManufacturers();
		
		theModel.addAttribute("maltManufacturers", theMaltManufacturers);
		
		return "malt-manufacturer-list";
	}
	
	@GetMapping("/showAddMaltManufacturerForm")
	public String showAddCountryForm(Model theModel) {

		MaltManufacturer theMaltManufacturer = new MaltManufacturer();
		
		theModel.addAttribute("maltManufacturer", theMaltManufacturer);
		
		return "malt-manufacturer-form";
	}
	
	@PostMapping("/saveMaltManufacturer")
	public String saveCountry(@Valid @ModelAttribute("maltManufacturer") MaltManufacturer theMaltManufacturer, BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()) {
			return "malt-manufacturer-form";
		}
		else {
			try {
				maltManufacturerService.saveMaltManufacturer(theMaltManufacturer);
				
				return "redirect:/maltManufacturer/list";
				
			} catch (ConstraintViolationException e) {
				theBindingResult.rejectValue("manufacturerName", "duplicate", "Duplicate manufacturer name");
		        return "malt-manufacturer-form";
			}
		}
	}
	
	@GetMapping("/showMaltManufacturerUpdateForm")
	public String showMaltManufacturerUpdateForm(@RequestParam("maltManufacturerId") int theId,
			Model theModel) {
		
		MaltManufacturer theMaltManufacturer = maltManufacturerService.getMaltManufacturer(theId);
		
		theModel.addAttribute("maltManufacturer", theMaltManufacturer);
		
		List<Integer> theMaltManufacturersId = maltManufacturerService.getMaltManufacturersId();
				
		if ( (!theMaltManufacturersId.contains(new Integer(theId)) ) || ( theId < 0 ) ) {
			throw new CountryNotFoundException("Malt manufacturer id not found - " + theId);
		}
		else {
			return "malt-manufacturer-form";	
		}
	}
	
	@GetMapping("/delete")
	public String deleteMaltManufacturer(@RequestParam("maltManufacturerId") int theId ) {
		
		maltManufacturerService.deleteMaltManufacturer(theId);
		
		return "redirect:/maltManufacturer/list";
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