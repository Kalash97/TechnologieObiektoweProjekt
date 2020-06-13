package com.controller.actions.teamActions;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTeamAction extends Action {

	private View view;
	private TeamRepo teamRepo;
	private SoldierRepo soldierRepo;

	@Override
	public void launch() {

		Team t = RepoUtil.getValidTeam(view, teamRepo);
		
		RepoUtil.removeCommanderFromTeam(t, soldierRepo);
		RepoUtil.removeSoldiersFromTeam(t, soldierRepo, teamRepo);
		
		teamRepo.delete(t);
	}

	@Override
	public String getName() {
		return "DeleteTeam";
	}

}
