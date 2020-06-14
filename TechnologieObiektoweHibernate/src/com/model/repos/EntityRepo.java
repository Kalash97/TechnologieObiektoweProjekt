package com.model.repos;

import lombok.AllArgsConstructor;

import com.model.entities.Persistable;
import com.model.persistanceManager.HibernatePersistanceManager;

@AllArgsConstructor
public abstract class EntityRepo <T extends Persistable> {
	
	HibernatePersistanceManager persistence;
	
	@SuppressWarnings("unchecked")
	public final T create(Persistable entity){
		return (T) persistence.create(entity);
	}
	
	public final void update(Persistable entity){
		persistence.update(entity);
	}
	
	public final void delete(Persistable entity){
		persistence.delete(entity);
	}
}
