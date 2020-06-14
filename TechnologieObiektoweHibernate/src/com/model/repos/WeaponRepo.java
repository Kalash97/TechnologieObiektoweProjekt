package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Weapon;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

public class WeaponRepo extends EntityRepo<Weapon> {
	
	public WeaponRepo(HibernatePersistanceManager persistence) {
		super(persistence);
	}

	public Weapon findById(long id) {
		return (Weapon) persistence.findById(id, Weapon.class);
	}
	
	public List<Weapon> findUnassignedWeapons(){
		String query = "SELECT W FROM Weapon W WHERE W.soldier.id=null";
		return findWeaponsByQuery(query);
	}

	private List<Weapon> findWeaponsByQuery(String query) {
		List<Persistable> results = persistence.findByQuery(query, Weapon.class);
		List<Weapon> weapons = ParseUtil.parseWeaponList(results);
		return weapons;
	}
	
	public List<Weapon> findAllWeapons() {
		String query = "SELECT W FROM Weapon W";
		return findWeaponsByQuery(query);
	}
}
