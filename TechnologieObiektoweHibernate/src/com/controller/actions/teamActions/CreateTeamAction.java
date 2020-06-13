package com.controller.actions.teamActions;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTeamAction extends Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {
		Team t = new Team();
		t.setNumber(view.getValidNumber("Podaj Id"));
		
		repo.create(t);
	}

	@Override
	public String getName() {
		return "CreateTeam";
	}

}
