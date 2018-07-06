package com.maruszka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maruszka.dao.CountryDAO;
import com.maruszka.entity.Country;
import com.maruszka.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;
	
	@Override
	@Transactional
	public List<Country> getCountries() {
		return countryDAO.getCountries();
	}

	@Override
	@Transactional
	public List<Country> getCoutriesNames() {
		return countryDAO.getCoutriesNames();
	}
	
	@Override
	@Transactional
	public void saveCountry(Country theCountry) {
		countryDAO.saveCountry(theCountry);
	}

	@Override
	@Transactional
	public Country getCountry(int theId) {
		return countryDAO.getCountry(theId);
	}

	@Override
	@Transactional
	public void deleteCountry(int theId) {
		countryDAO.deleteCountry(theId);
	}


}
