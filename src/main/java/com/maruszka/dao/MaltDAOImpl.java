package com.maruszka.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maruszka.dao.MaltDAO;
import com.maruszka.entity.Country;
import com.maruszka.entity.Malt;

@Repository
public class MaltDAOImpl extends AbstractDao<Integer, Malt> implements MaltDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public List<Malt> getMalts() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query and sort by malt name
		Query<Malt> theQuery = currentSession.createQuery("from Malt order by maltName", Malt.class);
		
		// execute query and get result list
		List<Malt> malts = theQuery.getResultList();
		
		//return the result
		return malts;
	}

	@Override
	public void saveMalt(Malt theMalt) {
		
		// get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save or update malt
		currentSession.saveOrUpdate(theMalt);
	}

	@Override
	public Malt getMalt(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve from DB using malt name
		Malt theMalt = currentSession.get(Malt.class, theId);
		
		// get the result
		return theMalt;
	}
	
	@Override
	public Malt getMaltByName(String maltName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve from DB using malt name
		Malt theMalt = currentSession.get(Malt.class, maltName);
		
		// get the result
		return theMalt;
	}

	@Override
	public void deleteMalt(int theId) {

		// get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with malt name
		Query theQuery = currentSession.createQuery("delete from Malt where id=:maltId");
		theQuery.setParameter("maltId", theId);
		
		theQuery.executeUpdate();
	}
	
	@Override
	public List<Malt> getMaltsNames() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Malt> theQuery = currentSession.createQuery("SELECT maltName FROM Malt");
		
		List<Malt> maltsNames = theQuery.getResultList();
		
		return maltsNames;
	}

}
