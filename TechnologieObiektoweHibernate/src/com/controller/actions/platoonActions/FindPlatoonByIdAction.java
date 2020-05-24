package com.controller.actions.platoonActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.RepoUtil;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonByIdAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = RepoUtil.getValidPlatoon(view, repo);
		
		ViewHelper.printResults(Arrays.asList(p), view);
		view.print("");
		
		try {
			view.print("-----Kompania:");
			ViewHelper.printResults(Arrays.asList(p.getCompany()), view);
		}catch (NullPointerException e) {
			view.print("Brak kompanii");
		}
		view.print("");
		
		view.print("-----Dru¿yny:");
		ViewHelper.printResults(p.getTeams(), view);		
		view.print("");
	}
	
	@Override
	public String getName() {
		return "FindPlatoonById";
	}

}
