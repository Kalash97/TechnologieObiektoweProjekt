package com.Repos;

import com.Entities.Platoon;
import com.PersistanceManager.HibernatePersistanceManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlatoonRepo {
	HibernatePersistanceManager hpm;
	
	public Platoon createPlatoon(Platoon platoon) {
		return (Platoon) hpm.create(platoon);
	}
	
	public void deletePlatoon(Platoon platoon) {
		hpm.delete(platoon);
	}
	
	public Platoon findById(long id) {
		return (Platoon) hpm.findById(id, Platoon.class);
	}
	
	public void updatePlatoon(Platoon p) {
		hpm.update(p);
	}
	
}
