package com.Repos;

import com.Entities.Soldier;
import com.PersistanceManager.HibernatePersistanceManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SoldierRepo {

	private HibernatePersistanceManager hpm;
	
	public Soldier createSoldier(Soldier soldier) {
		return (Soldier) hpm.create(soldier);
	}
	
	public void deleteSoldier(long id) {
		hpm.delete(id, Soldier.class);
	}
}
