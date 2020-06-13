package com.controller.actions;

import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.utils.enums.Rank;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignSoldierToTeamAction extends Action {

	private View view;
	private SoldierRepo soldierRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {
		Team t = RepoUtil.getValidTeam(view, teamRepo);
		Soldier s = RepoUtil.getValidSoldierWithLowerOrEqualRank(view, soldierRepo, Rank.MASTER_SERGEANT);

		s.setTeam(t);
		t.getSoldiers().add(s);
		teamRepo.update(t);
		soldierRepo.update(s);
	}
	
	@Override
	public String getName() {
		return "AssignSoldierToTeam";
	}

}
