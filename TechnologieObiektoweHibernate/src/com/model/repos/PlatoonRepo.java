package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Platoon;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

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
	
	public List<Platoon> findPlatoonsWithoutTeams(){
		String query = "SELECT P FROM Platoon P WHERE size(P.teams)=0";
		return findPlatoonsByQuery(query);
	}
	
	public List<Platoon> findPlatoonsWithoutCompany(){
		String query = "SELECT P FROM Platoon P WHERE P.company.id=null";
		return findPlatoonsByQuery(query);
	}

	public List<Platoon> findPlatoonsWithoutCommander(){
		String query = "SELECT P FROM Platoon P WHERE P.commander.id=null";
		return findPlatoonsByQuery(query);
	}
	
	private List<Platoon> findPlatoonsByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Platoon.class);
		List<Platoon> platoons = ParseUtil.parsePlatoonList(results);
		return platoons;
	}
}
