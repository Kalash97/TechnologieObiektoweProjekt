package com.controller.actions.teamActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllTeamsAction implements Action{

	private TeamRepo repo;
	private View view;
	
	@Override
	public void launch() {
		List<Team> teams = repo.findAllTeams();
		ViewHelper.printResults(teams, view);
		view.printDelimeter();
	}

	@Override
	public String getName() {
		return "FindAllTeams";
	}

}
