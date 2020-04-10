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
	
	public void deleteWeapon(long id) {
		hpm.delete(id, Weapon.class);
	}
}
