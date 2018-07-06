package com.maruszka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maruszka.dao.MaltDAO;
import com.maruszka.entity.Malt;
import com.maruszka.services.MaltService;

@Service
public class MaltServiceImpl implements MaltService {

	// inject malt DAO
	@Autowired
	private MaltDAO maltDAO;
	
	@Override
	@Transactional
	public List<Malt> getMalts() {
		return maltDAO.getMalts();
	}

	@Override
	@Transactional
	public void saveMalt(Malt theMalt) {
		maltDAO.saveMalt(theMalt);
	}

	@Override
	@Transactional
	public Malt getMalt(int theId) {
		return maltDAO.getMalt(theId);
	}

	@Override
	@Transactional
	public void deleteMalt(int theId) {
		maltDAO.deleteMalt(theId);
	}

	@Override
	@Transactional
	public List<Malt> getMaltsNames() {
		return maltDAO.getMaltsNames();
	}

}
