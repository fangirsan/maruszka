package com.maruszka.dao;

import java.util.List;

import com.maruszka.entity.Batch;

public interface BatchDAO {

	public List<Batch> getBatches();
	
	public void saveBatch (Batch theBatch);
	
	public Batch getBatch(int theId);
	
	public void deleteBatch(int theId);
}
