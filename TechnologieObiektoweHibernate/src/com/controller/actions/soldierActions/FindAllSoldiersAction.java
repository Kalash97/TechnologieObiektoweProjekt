package com.controller.actions.soldierActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllSoldiersAction implements Action{

	private SoldierRepo repo;
	private View view;
	
	@Override
	public void launch() {
		List<Soldier> soldiers = repo.findAllSoldiers();
		ViewHelper.printResults(soldiers, view);
		view.printDelimeter();
	}

	@Override
	public String getName() {
		return "FindAllSoldiers";
	}

}
