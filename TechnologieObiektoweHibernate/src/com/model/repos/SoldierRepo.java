package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Soldier;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

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

	public List<Soldier> findSoldiersByName(String name, String lastName){
		String query = "SELECT S FROM Soldier S WHERE S.name = " + "'" +name + "'" + " AND S.lastName = " + "'" + lastName + "'";
		return findSoldiersByQuery(query);
	}
	
	public void updateSoldier(Soldier soldier) {
		hpm.update(soldier);
	}
	
	public List<Soldier> findSoldiersWithoutTeam(){
		String query = "SELECT S FROM Soldier S WHERE S.team.id=null";
		return findSoldiersByQuery(query);
	}
	
	public List<Soldier> findSoldiersWithoutWeapon(){
		String query = "SELECT S FROM Soldier S WHERE size(S.weapons)=0";
		return findSoldiersByQuery(query);
	}

	private List<Soldier> findSoldiersByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Soldier.class);
		List<Soldier> soldiers = ParseUtil.parseSoldierList(results);
		return soldiers;
	}
	
	public List<Soldier> findAllSoldiers() {
		String query = "SELECT S FROM Soldier S";
		return findSoldiersByQuery(query);
	}
	
}
