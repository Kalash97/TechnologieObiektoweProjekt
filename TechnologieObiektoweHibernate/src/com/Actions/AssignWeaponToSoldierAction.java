package com.Actions;

import com.Entities.Soldier;
import com.Entities.Weapon;
import com.Repos.SoldierRepo;
import com.Repos.WeaponRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignWeaponToSoldierAction implements Action{

	private View view;
	private WeaponRepo weaponRepo;
	private SoldierRepo soldierRepo; 
	
	@Override
	public void launch() {
		
		Soldier s;
		Weapon w;
		String line;
		
		do {
			do {
				view.print("Podaj id ¿o³nierza do przypisania broni.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		
		do {
			do {
				view.print("Podaj id broni do przypisania.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			w = weaponRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(w));
		
		w.setSoldier(s);
		s.getWeapons().add(w);
		
		soldierRepo.updateSoldier(s);
		weaponRepo.updateWeapon(w);
	}

	@Override
	public String getName() {
		return "AssignWeaponToSoldier";
	}

}
