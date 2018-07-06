package com.maruszka.services;

import java.util.List;

import com.maruszka.entity.Country;

public interface CountryService {

	public List<Country> getCountries();
	
	public List<Country> getCoutriesNames();
	
	public void saveCountry (Country theCountry);
	
	public Country getCountry(int theId);
	
	public void deleteCountry(int theId);
	
}
