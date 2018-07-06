package com.maruszka.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maruszka.entity.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Country> getCountries() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Country> theQuery = currentSession.createQuery("from Country order by countryName", Country.class);
		
		List<Country> countries = theQuery.getResultList();
		
		return countries;
	}
	
	@Override
	public List<Country> getCoutriesNames() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Country> theQuery = currentSession.createQuery("SELECT countryName FROM Country");
		
		List<Country> countriesNames = theQuery.getResultList();
		
		return countriesNames;
	}

	@Override
	public void saveCountry(Country theCountry) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCountry);
	}

	@Override
	public Country getCountry(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Country theCountry = currentSession.get(Country.class, theId);
		
		return theCountry;
	}

	@Override
	public void deleteCountry(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Country> theQuery = currentSession.createQuery("delete from Country where id=:countryId");
		theQuery.setParameter("countryId", theId);
		
		theQuery.executeUpdate();
	}

}
