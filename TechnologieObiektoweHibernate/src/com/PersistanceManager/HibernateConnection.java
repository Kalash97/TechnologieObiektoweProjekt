package com.PersistanceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateConnection {
	
	public static EntityManager getManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("techobdb");
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
}
