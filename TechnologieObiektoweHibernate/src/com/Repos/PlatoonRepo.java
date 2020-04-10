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
	
	public void deletePlatoon(long id) {
		hpm.delete(id, Platoon.class);
	}
}
