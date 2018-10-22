package com.maruszka;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.maruszka.config.AppConfigTest;
import com.maruszka.dao.BatchDAO;
import com.maruszka.entity.Batch;
import com.maruszka.entity.Malt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfigTest.class},
					  loader = AnnotationConfigContextLoader.class)
@Transactional
public class CreateBatchAndMalt {

	@Resource
	private BatchDAO batchDao;
	
	@Test
	public void addBatch() {
		
		Batch tempBatch = new Batch();
		tempBatch.setBatchName("dupa");
		batchDao.saveBatch(tempBatch);
	}

	private static Boolean maltExists(Session session, String maltName) {
					
		System.out.println("Checking if malt exist...");
		
		Query query = session.createQuery("from Malt where maltName = :maltName");
		query.setParameter("maltName", maltName);
		List<Malt> theMalt =  query.getResultList();
		
		if (theMalt.size() == 0) {
			return false;
		} else {
			return true;
		}
			
	}

	
}
