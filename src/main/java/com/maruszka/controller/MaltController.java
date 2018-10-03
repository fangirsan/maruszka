package com.maruszka.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maruszka.entity.Malt;
import com.maruszka.entity.MaltManufacturer;
import com.maruszka.services.MaltManufacturerService;
import com.maruszka.services.MaltService;

@Controller
@RequestMapping("/malt")
public class MaltController {

	// need to inject malt service
	@Autowired
	private MaltService maltService;
	
	@Autowired
	private MaltManufacturerService maltManufacturerService;
	
	@GetMapping("/list")
	public String listMalts(Model theModel) {
		
		// get malt from service
		List<Malt> theMalts = maltService.getMalts();
		
		// add the malts to the model
		theModel.addAttribute("malts", theMalts);
		
		return "malts-list";
	}
	
	@GetMapping("/showAddMaltForm")
	public String showAddMaltForm(Model theModel) {
		
		// create model attribute to bind form data
		Malt theMalt = new Malt();
		
		theModel.addAttribute("malt", theMalt);
		
		return "malt-form";
	}
	
	// mapping is related to action in a form
	// responsible to add malt.
	// ModelAttribute "malt" is also related to the 
	// model attribute in a form
	@PostMapping("/saveMalt")
	public String saveMalt(@Valid @ModelAttribute("malt") Malt theMalt, BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()) {
			return "malt-form";
		}
		else {
			try {
				maltService.saveMalt(theMalt);
				
				return "redirect:/malt/list";
			} catch (ConstraintViolationException e) {
				theBindingResult.rejectValue("maltName", "duplicate", "Duplicate name");
				return "malt-form";
			}
		}
	}
	
	@GetMapping("/showMaltUpdateForm")
	public String showMaltUpdateForm(@RequestParam("maltId") int theId, Model theModel) {
		
		// get malt form our service
		Malt theMalt = maltService.getMalt(theId);
		
		// set malt as a model to prepopulate the form
		theModel.addAttribute("malt", theMalt);

		return "malt-form";
	}
	
	@GetMapping("/delete")
	public String deleteMalt(@RequestParam("maltId") int theId ) {
		
		// delete malt
		maltService.deleteMalt(theId);
		
		return "redirect:/malt/list";
	}
	
	@ModelAttribute("MaltManufacturerList")
	public List<MaltManufacturer> getMaltManufacturerNames()	{
		
		List<MaltManufacturer> maltManufacturerNames = maltManufacturerService.getMaltManufacturerNames();

		return maltManufacturerNames;
	}
	
}
