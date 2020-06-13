package com.controller.actions.soldierActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldiersWithoutWeaponAction extends Action{

	private View view;
	private SoldierRepo soldierRepo;
	
	@Override
	public void launch() {
		List<Soldier> soldiers = soldierRepo.findSoldiersWithoutWeapon();
		ViewHelper.printResults(soldiers, view);
	}

	@Override
	public String getName() {
		return "FindSoldiersWithoutWeapon";
	}

}
