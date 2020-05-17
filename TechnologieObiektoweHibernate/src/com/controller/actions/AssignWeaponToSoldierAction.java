package com.controller.actions;

import com.model.entities.Soldier;
import com.model.entities.Weapon;
import com.model.repos.SoldierRepo;
import com.model.repos.WeaponRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

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
		
		
		s = getValidSoldier();
		
		w = getValidWeapon();
		
		assignWeaponToSoldier(s, w);
		
		soldierRepo.updateSoldier(s);
		weaponRepo.updateWeapon(w);
	}

	private void assignWeaponToSoldier(Soldier s, Weapon w) {
		w.setSoldier(s);
		s.getWeapons().add(w);
	}

	private Weapon getValidWeapon() {
		Weapon w;
		String line;
		do {
			do {
				view.print("Podaj id broni do przypisania.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			w = weaponRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(w));
		return w;
	}

	private Soldier getValidSoldier() {
		Soldier s;
		String line;
		do {
			do {
				view.print("Podaj id ¿o³nierza do przypisania broni.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling assignWeapon");
		}
	}
	
	@Override
	public String getName() {
		return "AssignWeaponToSoldier";
	}

}
