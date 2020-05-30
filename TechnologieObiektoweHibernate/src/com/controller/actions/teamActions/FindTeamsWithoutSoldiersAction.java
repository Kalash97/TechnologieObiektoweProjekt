package com.controller.actions.teamActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamsWithoutSoldiersAction implements Action{

	private View view;
	private TeamRepo teamRepo;
	
	@Override
	public void launch() {
		List<Team> teams = teamRepo.findTeamsWithoutSoldiers();
		ViewHelper.printResults(teams, view);
	}
	
	@Override
	public String getName() {
		return "FindTeamsWithoutSoldiers";
	}

}
