package com.maruszka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maruszka.entity.Batch;
import com.maruszka.services.BatchService;

@Controller
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	private BatchService batchService;
	
	@GetMapping("/list")
	public String getBatches(Model theModel) {
		
		List<Batch> theBatches = batchService.getBatches();
		
		theModel.addAttribute("batches", theBatches);
		
		return "batch-list";
	}
}
