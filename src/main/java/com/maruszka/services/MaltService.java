package com.maruszka.services;

import java.util.List;

import com.maruszka.entity.Malt;

public interface MaltService {

	public List<Malt> getMalts();
	
	public void saveMalt (Malt theMalt);
	
	public Malt getMalt(int theId);
	
	public Malt getMaltbyName(String maltName);
	
	public void deleteMalt(int theId);

	public List<Malt> getMaltsNames();
}