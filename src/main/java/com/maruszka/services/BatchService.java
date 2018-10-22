package com.maruszka.services;

import java.util.List;

import javax.validation.Valid;

import com.maruszka.entity.Batch;

public interface BatchService {

	public List<Batch> getBatches();
	
	public void saveBatch (@Valid Batch theBatch);
	
	public Batch getBatch(int theId);
	
	public Batch getBatchByNumber(int batchNumber);
	
	public void deleteBatch(int theId);
	
	boolean isBatchNumberUnique(Integer id, Integer batchNumber);

}
