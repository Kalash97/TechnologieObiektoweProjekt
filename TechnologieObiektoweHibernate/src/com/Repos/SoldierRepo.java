package com.Repos;

import java.util.ArrayList;
import java.util.List;

import com.Entities.Persistable;
import com.Entities.Platoon;
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

	public void updateSoldier(Soldier soldier) {
		hpm.update(soldier);
	}

	public List<Platoon> findPlatoonOfCommander(Soldier soldier) {
		String query = "SELECT P FROM Platoon P, Soldier S WHERE P.commander.id = " + soldier.getId();
		List<Persistable> results = hpm.findByQuery(query, Platoon.class);
		List<Platoon> platoons = new ArrayList<Platoon>();
		if (results.size() > 0) {
			for (int i = 0; i < results.size(); i++) {
				Platoon p = (Platoon) results.get(i);
				platoons.add(p);
			}
		}
		return platoons;
	}
}
