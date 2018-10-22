package com.maruszka.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.maruszka.entity.Batch;
import com.maruszka.entity.Malt;

public class CreateBatchAndMalt {

	public static void main(String[] args) {
		

		
		// create session factory
		SessionFactory factory = null;
		try {
			factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Batch.class)
					.addAnnotatedClass(Malt.class)
					.buildSessionFactory();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		// create session
		Session session = factory.getCurrentSession();
		
		
			try {			
				
				// start a transaction
				session.beginTransaction();
				
				// create a malt
				String tempMaltName = "Pale Ale";	
				if ( !maltExists(session, tempMaltName) ) {
					
					Malt tempMalt = new Malt(tempMaltName, "Strzegom", 100, 5, "All");
					System.out.println(tempMalt.toString());
					
					// save the malt
					System.out.println("\nSaving the malt ...");
					session.save(tempMalt);
					System.out.println("Saved the malt: " + tempMalt);
					
				} else {
					System.out.println("Malt: " + tempMaltName + " exists!" );
			}
				
				// create the batch
//				Date now = new Date();
//				Batch tempBatch = new Batch(2, "Mild", "Szatan", now);
//				
//				// add malt to the batch
//				tempBatch.addMalt(tempMalt);
//				
//				// save the batch
//				System.out.println("\nSaving batch ...");
//				session.save(tempBatch);
//				System.out.println("Saved batch: " + tempBatch);
//				
//				// commit transaction
//				session.getTransaction().commit();
//				
//				System.out.println("Done!");
			}
			finally {
				
				// close session
				session.close();
				System.out.println("Connection closed");
				
				factory.close();
			}
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
