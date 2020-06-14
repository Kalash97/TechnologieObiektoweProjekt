package com.model.repos;

import java.util.List;

import com.model.entities.Battalion;
import com.model.entities.Persistable;
import com.model.entities.Soldier;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

public class BattalionRepo extends EntityRepo<Battalion> {
	
	public BattalionRepo(HibernatePersistanceManager persistence) {
		super(persistence);
	}

	public Battalion findById(long id) {
		return (Battalion) persistence.findById(id, Battalion.class);
	}
	
	public List<Battalion> findBattalionsWithoutCompanies(){
		String query = "SELECT B FROM Battalion B WHERE size(B.companies)=0";
		return findBattalionsByQuery(query);
	}
	
	public List<Battalion> findBattalionsWithoutCommander(){
		String query = "SELECT B FROM Battalion B WHERE B.commander.id=null";
		return findBattalionsByQuery(query);
	}
	
	public List<Battalion> findBattalionOfCommander(Soldier soldier){
		String query = "SELECT B FROM Battalion B, Soldier S WHERE B.commander.id = " + soldier.getId() + " AND S.id = " + soldier.getId();
		return findBattalionsByQuery(query);
	}
	
	public List<Battalion> findAllBattalions() {
		String query = "SELECT B FROM Battalion B";
		return findBattalionsByQuery(query);
	}
	
	private List<Battalion> findBattalionsByQuery(String query){
		List<Persistable> results = persistence.findByQuery(query, Battalion.class);
		List<Battalion> battalions = ParseUtil.parseBattalionList(results);
		return battalions;
	}
}
