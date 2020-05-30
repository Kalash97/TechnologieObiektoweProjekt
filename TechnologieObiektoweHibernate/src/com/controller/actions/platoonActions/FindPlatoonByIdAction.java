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
		Platoon p = RepoUtil.getValidPlatoon(view,repo);
		
		ViewHelper.printResults(Arrays.asList(p), view);
		view.printDelimeter();
		
		try {
			ViewHelper.printResults("-----Kompania:",Arrays.asList(p.getCompany()), view);
		}catch (NullPointerException e) {
			view.print("Brak kompanii");
		}
		view.printDelimeter();
		
		ViewHelper.printResults("-----Dru¿yny:",p.getTeams(), view);		
		view.printDelimeter();
	}
	
	@Override
	public String getName() {
		return "FindPlatoonById";
	}

}
