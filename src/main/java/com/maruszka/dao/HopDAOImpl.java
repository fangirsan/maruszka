package com.maruszka.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maruszka.dao.HopDAO;
import com.maruszka.entity.Hop;

@Repository
public class HopDAOImpl implements HopDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Hop> getHops() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query and sort by hop name
		Query<Hop> theQuery = currentSession.createQuery("from Hop order by hopName", Hop.class);
		
		// execute query and get result list
		List<Hop> hops = theQuery.getResultList();
		
		//return the result
		return hops;
	}

	@Override
	public void saveHop(Hop theHop) {

		// get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save or update malt
		currentSession.saveOrUpdate(theHop);
	}

	@Override
	public Hop getHop(int theId) {
	
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve from DB using malt name
		Hop theHop = currentSession.get(Hop.class, theId);
		
		// get the result
		return theHop;
	}

	@Override
	public void deleteHop(int theId) {

		// get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with hop name
		Query<?> theQuery = currentSession.createQuery("delete from Hop where id=:hopId");
		theQuery.setParameter("hopId", theId);
		
		theQuery.executeUpdate();
	}
	
}
