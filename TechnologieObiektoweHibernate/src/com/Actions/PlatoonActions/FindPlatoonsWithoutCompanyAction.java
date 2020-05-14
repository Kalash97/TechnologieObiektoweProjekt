package com.Actions.PlatoonActions;

import java.util.List;

import com.Actions.Action;
import com.Entities.Platoon;
import com.Repos.PlatoonRepo;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonsWithoutCompanyAction implements Action{

	private View view;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {
		List<Platoon> platoons = platoonRepo.findPlatoonsWithoutCompany();
		ViewHelper.printResults(ViewHelper.platoonsToPersistable(platoons), view);
	}

	@Override
	public String getName() {
		return "FindPlatoonsWithoutCompany";
	}

}
