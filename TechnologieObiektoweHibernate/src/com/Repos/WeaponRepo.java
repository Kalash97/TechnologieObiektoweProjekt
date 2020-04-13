package com.Repos;

import com.Entities.Weapon;
import com.PersistanceManager.HibernatePersistanceManager;

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
}
