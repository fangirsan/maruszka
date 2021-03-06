package com.maruszka.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maruszka.entity.Batch;
import com.maruszka.entity.Malt;
import com.maruszka.services.BatchService;
import com.maruszka.services.MaltService;

@Controller
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	private BatchService batchService;
	
	@Autowired
	private MaltService maltService;
	
//	@Autowired
//	BatchFormValidator batchFormValidator;
//	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimerEditor);
	}
	
	@GetMapping("/list")
	public String getBatches(Model theModel) {
		
		List<Batch> theBatches = batchService.getBatches();
		
		theModel.addAttribute("batches", theBatches);
		
		return "batch-list";
	}
	
	@GetMapping("/showAddBatchForm")
	public String showAddBatchForm(Model theModel) {
		
		// create model attribute to bind form data
		Batch theBatch = new Batch();
		
		theModel.addAttribute("batch", theBatch);
		
		return "batch-form";
	}
	
	// mapping is related to action in a form
	// responsible to add batch.
	// ModelAttribute "batch" is also related to the 
	// model attribute in a form
	@PostMapping("/saveBatch")
	public String saveBatch(@Valid @ModelAttribute("batch") Batch theBatch, BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()) {
	        return "batch-form";
	    }
		else {
			try {
				batchService.saveBatch(theBatch);
				
				return "redirect:/batch/list";
				
			} catch (ConstraintViolationException e) {
				theBindingResult.rejectValue("batchNumber", "duplicate", "Duplicate batch number");
		        return "batch-form";
		    } catch (DataIntegrityViolationException  e) {
		    	theBindingResult.rejectValue("batchNumber", "duplicate", "Duplicate batch number");
		        return "batch-form";
		    }
		}
	}
	
	@GetMapping("/showBatchUpdateForm")
	public String showBatchUpdateForm(@RequestParam("batchId") int theId, Model theModel) {
		
		// get batch form our service
		Batch theBatch = batchService.getBatch(theId);
		
		// set  as a model to prepopulate the form
		theModel.addAttribute("batch", theBatch);

		return "batch-form";
	}
	
	@GetMapping("/delete")
	public String deleteBatch(@RequestParam("batchId") int theId ) {
		
		// delete batch
		batchService.deleteBatch(theId);
		
		return "redirect:/batch/list";
	}
	
	@ModelAttribute("maltList")
	public List<Malt> getMaltsNames()	{
		
		List<Malt> maltsNames = maltService.getMaltsNames();

		return maltsNames;
	}
}
