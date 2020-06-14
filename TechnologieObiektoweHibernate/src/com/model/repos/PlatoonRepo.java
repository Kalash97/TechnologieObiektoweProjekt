package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

public class PlatoonRepo extends EntityRepo<Platoon>{

	public PlatoonRepo(HibernatePersistanceManager persistence) {
		super(persistence);
	}
	
	public Platoon findById(long id) {
		return (Platoon) persistence.findById(id, Platoon.class);
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
		List<Persistable> results = persistence.findByQuery(query, Platoon.class);
		List<Platoon> platoons = ParseUtil.parsePlatoonList(results);
		return platoons;
	}
	
	public List<Platoon> findPlatoonOfCommander(Soldier soldier) {
		String query = "SELECT P FROM Platoon P, Soldier S WHERE P.commander.id = " + soldier.getId() + " AND S.id = " + soldier.getId();
		return findPlatoonsByQuery(query);
	}
	
	public List<Platoon> findAllPlatoons() {
		String query = "SELECT P FROM Platoon P";
		return findPlatoonsByQuery(query);
	}
}
