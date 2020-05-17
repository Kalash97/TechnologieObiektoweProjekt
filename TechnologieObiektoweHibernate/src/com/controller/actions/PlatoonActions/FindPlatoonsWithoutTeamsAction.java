package com.controller.actions.PlatoonActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonsWithoutTeamsAction implements Action{

	private View view;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {
		List<Platoon> platoons = platoonRepo.findPlatoonsWithoutTeams();
		ViewHelper.printResults(ViewHelper.platoonsToPersistable(platoons), view);
	}

	@Override
	public String getName() {
		return "FindPlatoonsWithoutTeams";
	}

}
