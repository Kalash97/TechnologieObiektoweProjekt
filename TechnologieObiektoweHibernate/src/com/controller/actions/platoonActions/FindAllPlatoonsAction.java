package com.controller.actions.platoonActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllPlatoonsAction implements Action{

	private PlatoonRepo repo;
	private View view;
	
	@Override
	public void launch() {
		List<Platoon> platoons = repo.findAllPlatoons();
		ViewHelper.printResults(platoons, view);
		view.print("");
	}

	@Override
	public String getName() {
		return "FindAllPlatoons";
	}

}
