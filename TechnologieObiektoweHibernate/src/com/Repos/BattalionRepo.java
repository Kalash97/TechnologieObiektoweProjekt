package com.Repos;

import java.util.List;

import com.Entities.Battalion;
import com.Entities.Persistable;
import com.PersistanceManager.HibernatePersistanceManager;
import com.Utils.ParseUtil;

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
	
	public void updateBattalion(Battalion battalion) {
		hpm.update(battalion);
	}
	
	public List<Battalion> findBattalionsWithoutCompanies(){
		String query = "SELECT B FROM Battalion B WHERE size(B.companies)=0";
		return findbattalionsByQuery(query);
	}
	
	public List<Battalion> findBattalionsWithoutCommander(){
		String query = "SELECT B FROM Battalion B WHERE B.commander.id=null";
		return findbattalionsByQuery(query);
	}
	
	private List<Battalion> findbattalionsByQuery(String query){
		List<Persistable> results = hpm.findByQuery(query, Battalion.class);
		List<Battalion> battalions = ParseUtil.parseBattalionList(results);
		return battalions;
	}
}
