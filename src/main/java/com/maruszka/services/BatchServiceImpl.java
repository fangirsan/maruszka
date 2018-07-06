package com.maruszka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maruszka.dao.BatchDAO;
import com.maruszka.entity.Batch;
import com.maruszka.services.BatchService;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	BatchDAO batchDAO;

	@Override
	@Transactional
	public List<Batch> getBatches() {
		return batchDAO.getBatches();
	}

	@Override
	@Transactional
	public void saveBatch(Batch theBatch) {
		batchDAO.saveBatch(theBatch);
	}

	@Override
	@Transactional
	public Batch getBatch(int theId) {
		return batchDAO.getBatch(theId);
	}

	@Override
	@Transactional
	public void deleteBatch(int theId) {
		batchDAO.deleteBatch(theId);
	}
}
