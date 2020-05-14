package com.Actions.WeaponActions;

import java.util.List;

import com.Actions.Action;
import com.Entities.Weapon;
import com.Repos.WeaponRepo;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindWeaponsWithoutSoldierAction implements Action{

	private View view;
	private WeaponRepo weaponRepo;
	
	@Override
	public void launch() {
		List<Weapon> weapons = weaponRepo.findUnassignedWeapons();
		ViewHelper.printResults(ViewHelper.weaponsToPersistable(weapons), view);
	}

	@Override
	public String getName() {
		return "FindWeaponsWithoutSoldier";
	}

}
