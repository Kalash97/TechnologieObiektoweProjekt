package com.controller.actions;

import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.utils.enums.Rank;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommanderToTeamAction implements Action {

	private View view;
	private TeamRepo teamRepo;
	private PlatoonRepo platoonRepo;
	private CompanyRepo companyRepo;
	private BattalionRepo battalionRepo;
	private SoldierRepo soldierRepo;

	@Override
	public void launch() {
		Team t = RepoUtil.getValidTeam(view, teamRepo);
		Soldier s = RepoUtil.getValidSoldierWithRankInRange(view, soldierRepo, Rank.CORPORAL, Rank.MASTER_SERGEANT);

		RepoUtil.detachCommanderFromTeams(s, teamRepo);
		RepoUtil.detachCommanderFromPlatoons(s, platoonRepo);
		RepoUtil.detachCommanderFromCompanies(s, companyRepo);
		RepoUtil.detachCommanderFromBattalions(s, battalionRepo);

		assignCommanderToTeam(t, s);

	}

	private void assignCommanderToTeam(Team t, Soldier s) {

		if (!t.getSoldiers().contains(s)) {	
			s.setTeam(t);
			t.getSoldiers().add(s);
		}
		t.setCommander(s);
		teamRepo.updateTeam(t);
		soldierRepo.updateSoldier(s);
	}

	@Override
	public String getName() {
		return "AssignCommanderToTeam";
	}

}
