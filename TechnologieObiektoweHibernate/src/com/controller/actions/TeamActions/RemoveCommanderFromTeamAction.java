package com.controller.actions.TeamActions;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromTeamAction implements Action{

	private View view;
	private TeamRepo repo;
	
	@Override
	public void launch() {
		Team t = RepoUtil.getValidTeam(view, repo);
		
		t.setCommander(null);
		repo.updateTeam(t);
	}

	@Override
	public String getName() {
		return "RemoveCommanderFromTeam";
	}

}
