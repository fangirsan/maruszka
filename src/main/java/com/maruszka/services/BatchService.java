package com.maruszka.services;

import java.util.List;

import javax.validation.Valid;

import com.maruszka.entity.Batch;

public interface BatchService {

	public List<Batch> getBatches();
	
	public void saveBatch (@Valid Batch theBatch);
	
	public Batch getBatch(int theId);
	
	public void deleteBatch(int theId);

}
