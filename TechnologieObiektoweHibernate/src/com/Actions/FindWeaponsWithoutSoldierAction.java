package com.Actions;

import java.util.List;

import com.Entities.Weapon;
import com.Repos.WeaponRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindWeaponsWithoutSoldierAction implements Action{

	private View view;
	private WeaponRepo weaponRepo;
	
	@Override
	public void launch() {
		List<Weapon> unassignedWeapons = weaponRepo.findUnassignedWeapons();
		showUnassignedWeapons(unassignedWeapons);
	}

	private void showUnassignedWeapons(List<Weapon> unassignedWeapons) {
		if(unassignedWeapons.size()>0) {
			for(int i=0; i<unassignedWeapons.size();i++) {
				Weapon w = unassignedWeapons.get(i);
				view.print(i+": ID: " +w.getId()+", Nazwa: "+w.getName()+", Nr. seryjny: "+w.getSerialNumber()+", Typ: "+ w.getWeaponType());
			}
		}else {
			view.print("Brak nieprzypisanej broni.");
		}
	}

	@Override
	public String getName() {
		return "FindWeaponsWithoutSoldier";
	}

}
