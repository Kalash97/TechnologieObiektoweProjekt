package com.controller.actions.soldierActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.RepoUtil;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldierByIdAction implements Action {

	private View view;
	private SoldierRepo repo;

	@Override
	public void launch() {
		Soldier s = RepoUtil.getValidSoldier(view, repo);

		ViewHelper.printResults(Arrays.asList(s), view);
		
		try {
			ViewHelper.printResults(Arrays.asList(s.getTeam()), view);
		} catch (NullPointerException e) {
			view.print("Brak dru¿yny");
		}
		
	}	

	@Override
	public String getName() {
		return "FindSoldierById";
	}

}
