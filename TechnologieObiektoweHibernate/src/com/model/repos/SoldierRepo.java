package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Soldier;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

import lombok.AllArgsConstructor;

public class SoldierRepo extends EntityRepo<Soldier>{

	public SoldierRepo(HibernatePersistanceManager persistence) {
		super(persistence);
	}

	public Soldier findById(long id) {
		return (Soldier) persistence.findById(id, Soldier.class);
	}

	public List<Soldier> findSoldiersByName(String name, String lastName){
		String query = "SELECT S FROM Soldier S WHERE S.name = " + "'" +name + "'" + " AND S.lastName = " + "'" + lastName + "'";
		return findSoldiersByQuery(query);
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
		List<Persistable> results = persistence.findByQuery(query, Soldier.class);
		List<Soldier> soldiers = ParseUtil.parseSoldierList(results);
		return soldiers;
	}
	
	public List<Soldier> findAllSoldiers() {
		String query = "SELECT S FROM Soldier S";
		return findSoldiersByQuery(query);
	}
}
