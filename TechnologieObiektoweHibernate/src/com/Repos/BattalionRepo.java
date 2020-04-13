package com.Repos;

import com.Entities.Battalion;
import com.PersistanceManager.HibernatePersistanceManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BattalionRepo {

	HibernatePersistanceManager hpm;
	
	public Battalion createBattalion(Battalion battalion) {
		return (Battalion) hpm.create(battalion);
	}
	
	public void deleteBattalion(Battalion battalion) {
		hpm.delete(battalion);
	}
	
	public Battalion findById(long id) {
		return (Battalion) hpm.findById(id, Battalion.class);
	}
}
