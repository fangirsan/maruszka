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
import com.maruszka.entity.Hop;
import com.maruszka.services.CountryService;
import com.maruszka.services.HopService;

@Controller
@RequestMapping("/hop")
public class HopController {

	// need to inject malt service
	@Autowired
	private HopService hopService;
	
	@Autowired CountryService countryService;
	
	@GetMapping("/list")
	public String listHops(Model theModel) {
		
		// get malt from service
		List<Hop> theHops = hopService.getHops();
		
		// add the malts to the model
		theModel.addAttribute("hops", theHops);
		
		return "hops-list";
	}
	
	@GetMapping("/showAddHopForm")
	public String showAddHopForm(Model theModel) {
		
		// create model attribute to bind form data
		Hop theHop = new Hop();
		
		theModel.addAttribute("hop", theHop);
		
		return "hop-form";
	}
	
	// mapping is related to action in a form
	// responsible to add hop.
	// ModelAttribute "hop" is also related to the 
	// model attribute in a form
	@PostMapping("/saveHop")
	public String saveHop(@Valid @ModelAttribute("hop") Hop theHop,
						  BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()) {
			return "hop-form";
		}
		else {
			
			hopService.saveHop(theHop);
			
			return "redirect:/hop/list";
		}
	}
	
	
	@GetMapping("/showHopUpdateForm")
	public String showMaltUpdateForm(@RequestParam("hopId") int theId,
									 Model theModel) {
		
		// get hop form our service
		Hop theHop = hopService.getHop(theId);
		
		// set hop as a model to prepopulate the form
		theModel.addAttribute("hop", theHop);

		return "hop-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteMalt(@RequestParam("hopId") int theId ) {
		
		// delete hop
		hopService.deleteHop(theId);
		
		return "redirect:/hop/list";
		
	}
	
	@ModelAttribute("countryList")
	public List<Country> getCountry()	{
		
		List<Country> countriesNames = countryService.getCoutriesNames();

		return countriesNames;
	}
}
