package com.controller.actions.WeaponActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindWeaponsWithoutSoldierAction implements Action{

	private View view;
	private WeaponRepo weaponRepo;
	
	@Override
	public void launch() {
		List<Weapon> weapons = weaponRepo.findUnassignedWeapons();
		ViewHelper.printResults(weapons, view);
	}

	@Override
	public String getName() {
		return "FindWeaponsWithoutSoldier";
	}

}
