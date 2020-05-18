package com.model.repos;

import java.util.List;

import com.model.entities.Persistable;
import com.model.entities.Weapon;
import com.model.persistanceManager.HibernatePersistanceManager;
import com.utils.ParseUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WeaponRepo {
	
	private HibernatePersistanceManager hpm;
	
	public Weapon createWeapon(Weapon weapon) {
		return (Weapon) hpm.create(weapon);
	}
	
	public void deleteWeapon(Weapon weapon) {
		hpm.delete(weapon);
	}
	
	public Weapon findById(long id) {
		return (Weapon) hpm.findById(id, Weapon.class);
	}
	
	public void updateWeapon(Weapon weapon) {
		hpm.update(weapon);
	}
	
	public List<Weapon> findUnassignedWeapons(){
		String query = "SELECT W FROM Weapon W WHERE W.soldier.id=null";
		return findWeaponsByQuery(query);
	}

	private List<Weapon> findWeaponsByQuery(String query) {
		List<Persistable> results = hpm.findByQuery(query, Weapon.class);
		List<Weapon> weapons = ParseUtil.parseWeaponList(results);
		return weapons;
	}
	
	public List<Weapon> findAllWeapons() {
		String query = "SELECT W FROM Weapon W";
		return findWeaponsByQuery(query);
	}
}
