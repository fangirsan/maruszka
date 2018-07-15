package com.maruszka.dao;

import java.util.List;

import com.maruszka.entity.Country;

public interface CountryDAO {

	public List<Country> getCountries();
	
	public List<Country> getCoutriesNames();
	
	public List<Integer> getCoutriesId();
	
	public void saveCountry (Country theCountry);
	
	public Country getCountry(int theId);
	
	public void deleteCountry(int theId);

}
