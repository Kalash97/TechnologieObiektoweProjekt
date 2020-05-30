package com.controller.actions.weaponActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllWeaponsAction implements Action{

	private WeaponRepo repo;
	private View view;
	
	@Override
	public void launch() {
		List<Weapon> weapons = repo.findAllWeapons();
		ViewHelper.printResults(weapons, view);
		view.printDelimeter();
	}

	@Override
	public String getName() {
		return "FindAllWeapons";
	}

}
