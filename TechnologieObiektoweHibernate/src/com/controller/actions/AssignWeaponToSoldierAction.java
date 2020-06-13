package com.controller.actions;

import com.model.entities.Soldier;
import com.model.entities.Weapon;
import com.model.repos.SoldierRepo;
import com.model.repos.WeaponRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignWeaponToSoldierAction extends Action{

	private View view;
	private WeaponRepo weaponRepo;
	private SoldierRepo soldierRepo; 
	
	@Override
	public void launch() {
		
		Soldier s = RepoUtil.getValidSoldier(view, soldierRepo);
		Weapon w = RepoUtil.getValidWeapon(view, weaponRepo);
		
		w.setSoldier(s);
		s.getWeapons().add(w);
		soldierRepo.update(s);
		weaponRepo.update(w);
	}
	
	@Override
	public String getName() {
		return "AssignWeaponToSoldier";
	}

}
