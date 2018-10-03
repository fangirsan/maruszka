package com.maruszka.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maruszka.dao.MaltManufacturerDAO;
import com.maruszka.entity.MaltManufacturer;

@Service
public class MaltManufacturerServiceImpl implements MaltManufacturerService {

	@Autowired
	private MaltManufacturerDAO maltManufacturerDAO;
	
	@Override
	@Transactional
	public List<MaltManufacturer> getMaltManufacturers() {
		return maltManufacturerDAO.getMaltManufacturers();
	}

	@Override
	@Transactional
	public List<MaltManufacturer> getMaltManufacturerNames() {
		return maltManufacturerDAO.getMaltManufacturersNames();
	}
	
	@Override
	@Transactional
	public List<Integer> getMaltManufacturersId() {
		return maltManufacturerDAO.getMaltManufacturersId();
	}
	
	@Override
	@Transactional
	public void saveMaltManufacturer(@Valid MaltManufacturer theMaltManufacturer) {
		maltManufacturerDAO.saveMaltManufacturer(theMaltManufacturer);
	}

	@Override
	@Transactional
	public MaltManufacturer getMaltManufacturer(int theId) {
		return maltManufacturerDAO.getMaltManufacturer(theId);
	}

	@Override
	@Transactional
	public void deleteMaltManufacturer(int theId) {
		maltManufacturerDAO.deleteMaltManufacturer(theId);
	}
}
