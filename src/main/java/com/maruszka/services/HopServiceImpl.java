package com.maruszka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maruszka.dao.HopDAO;
import com.maruszka.entity.Hop;
import com.maruszka.services.HopService;

@Service
public class HopServiceImpl implements HopService {

	@Autowired
	private HopDAO hopDAO;
	
	@Override
	@Transactional
	public List<Hop> getHops() {
		return hopDAO.getHops();
	}

	@Override
	@Transactional
	public void saveHop(Hop theHop) {
		hopDAO.saveHop(theHop);
	}

	@Override
	@Transactional
	public Hop getHop(int theId) {
		return hopDAO.getHop(theId);
	}

	@Override
	@Transactional
	public void deleteHop(int theId) {
		hopDAO.deleteHop(theId);
	}
}
