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
	
	public void deleteSoldier(Soldier soldier) {
		hpm.delete(soldier);
	}
	
	public Soldier findById(long id) {
		return (Soldier) hpm.findById(id, Soldier.class);
	}
}
