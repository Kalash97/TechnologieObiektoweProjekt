package com.PersistanceManager;

import javax.persistence.EntityManager;

import com.Entities.Persistable;

public class HibernatePersistanceManager {
	
	public Persistable create(Persistable persistable) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.persist(persistable);
		em.getTransaction().commit();
		em.close();
		return persistable;
	}
	
	public void delete(Persistable persistable) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.remove(em.contains(persistable)?persistable:em.merge(persistable));
		em.getTransaction().commit();
		em.close();
	}
	
	//TMP
//	public void delete(long id, Class<?> type) {
//		EntityManager em = HibernateConnection.getManager();
//		em.getTransaction().begin();
//		Persistable persistable = (Persistable) em.find(type, id);
//		em.remove(persistable);
//		em.getTransaction().commit();
//		em.close();
//	}
	
	public Persistable findById(long id, Class<?> type) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Persistable persistable = (Persistable) em.find(type, id);
		em.getTransaction().commit();
		em.close();
		return persistable;
	}
}
