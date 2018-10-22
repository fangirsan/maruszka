package com.maruszka.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maruszka.entity.Batch;

@Repository
public class BatchDAOImpl extends AbstractDao<Integer, Batch> implements BatchDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Batch> getBatches() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Batch> theQuery = currentSession.createQuery("from Batch order by batchNumber", Batch.class);
		
		List<Batch> batches = theQuery.getResultList();
		
//		if(batches!=null){
//            Hibernate.initialize(batches.getMalts());
//        }
		
		return batches;
	}

	@Override
	public void saveBatch(Batch theBatch) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theBatch);
	}

	@Override
	public Batch getBatch(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Batch theBatch = currentSession.get(Batch.class, theId);
		
		if(theBatch!=null){
            Hibernate.initialize(theBatch.getMalts());
        }
		
		return theBatch;
	}
	
	@Override
	public Batch getBatchByNumber(int batchNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Batch theBatch = currentSession.get(Batch.class, batchNumber);
		
		if(theBatch!=null){
            Hibernate.initialize(theBatch.getMalts());
        }
		
		return theBatch;
	}

	@Override
	public void deleteBatch(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete FROM Batch where id=:batchId");
		theQuery.setParameter("batchId", theId);
		
		theQuery.executeUpdate();
	}

}
