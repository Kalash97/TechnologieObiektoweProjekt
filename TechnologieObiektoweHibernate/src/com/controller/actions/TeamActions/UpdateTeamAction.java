package com.controller.actions.TeamActions;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTeamAction implements Action{

	private View view;
	private TeamRepo repo;
	
	@Override
	public void launch() {
		Team t = RepoUtil.getValidTeam(view, repo);		
		t.setNumber(view.getValidNumber("Podaj Id"));
		
		
		repo.updateTeam(t);
	}

	@Override
	public String getName() {
		return "UpdateTeam";
	}

}
