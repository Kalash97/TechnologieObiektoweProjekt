package com.model.persistanceManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.model.entities.Persistable;

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
	
	public Persistable findById(long id, Class<?> type) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		Persistable persistable = (Persistable) em.find(type, id);
		em.getTransaction().commit();
		em.close();
		return persistable;
	}
	
	public void update(Persistable persistable) {
		EntityManager em = HibernateConnection.getManager();
		em.getTransaction().begin();
		em.merge(persistable);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Persistable> findByQuery(String query, Class<?> type){
		EntityManager em = HibernateConnection.getManager();
		
		 TypedQuery<?> typedQuery = em.createQuery(query, type);
		 
		 return (List<Persistable>) typedQuery.getResultList();
		
	}
}
