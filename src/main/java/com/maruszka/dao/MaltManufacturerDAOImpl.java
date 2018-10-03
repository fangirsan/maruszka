package com.maruszka.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maruszka.entity.MaltManufacturer;

@Repository
public class MaltManufacturerDAOImpl implements MaltManufacturerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<MaltManufacturer> getMaltManufacturers() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<MaltManufacturer> theQuery = currentSession.createQuery("from MaltManufacturer order by manufacturerName", MaltManufacturer.class);
		
		List<MaltManufacturer> maltManufacturers = theQuery.getResultList();
		
		return maltManufacturers;
	}
	
	@Override
	public List<MaltManufacturer> getMaltManufacturersNames() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<MaltManufacturer> theQuery = currentSession.createQuery("SELECT manufacturerName FROM MaltManufacturer");
		
		List<MaltManufacturer> maltManufacturersNames = theQuery.getResultList();
		
		return maltManufacturersNames;
	}
	
	@Override
	public List<Integer> getMaltManufacturersId() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Integer> theQuery = currentSession.createQuery("SELECT id FROM MaltManufacturer");
		
		List<Integer> maltManufacturersId = theQuery.getResultList();
		
		return maltManufacturersId;
	}

	@Override
	public void saveMaltManufacturer(MaltManufacturer theMaltManufacturer) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theMaltManufacturer);
	}

	@Override
	public MaltManufacturer getMaltManufacturer(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		MaltManufacturer theMaltManufacturer = currentSession.get(MaltManufacturer.class, theId);
		
		return theMaltManufacturer;
	}

	@Override
	public void deleteMaltManufacturer(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<MaltManufacturer> theQuery = currentSession.createQuery("delete from MaltManufacturer where id=:MaltManufacturerId");
		theQuery.setParameter("MaltManufacturerId", theId);
		
		theQuery.executeUpdate();
	}
}
