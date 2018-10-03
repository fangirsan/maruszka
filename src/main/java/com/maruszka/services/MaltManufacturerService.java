package com.maruszka.services;

import java.util.List;

import javax.validation.Valid;

import com.maruszka.entity.MaltManufacturer;

public interface MaltManufacturerService {

	public List<MaltManufacturer> getMaltManufacturers();
	
	public List<MaltManufacturer> getMaltManufacturerNames();
	
	public List<Integer> getMaltManufacturersId();

	public void saveMaltManufacturer(@Valid MaltManufacturer theMaltManufacturer);

	public MaltManufacturer getMaltManufacturer(int theId);

	public void deleteMaltManufacturer(int theId);
	
}
