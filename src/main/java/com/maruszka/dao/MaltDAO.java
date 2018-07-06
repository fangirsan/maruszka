package com.maruszka.dao;

import java.util.List;

import com.maruszka.entity.Malt;

public interface MaltDAO {

	public List<Malt> getMalts();
	
	public void saveMalt (Malt theMalt);
	
	public Malt getMalt(int theId);
	
	public void deleteMalt(int theId);
}
