package com.controller.actions;

import com.model.entities.Platoon;
import com.model.entities.Soldier;
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
public class AssignCommanderToPlatoonAction extends Action {

	private View view;
	private SoldierRepo soldierRepo;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {
		Platoon p = RepoUtil.getValidPlatoon(view, platoonRepo);
		Soldier s = RepoUtil.getValidSoldierWithRankInRange(view, soldierRepo, Rank.LT_2ND, Rank.LT_1ST);

		RepoUtil.detachCommanderFromTeams(s, teamRepo);
		RepoUtil.detachSoldierFromTeams(s, teamRepo, soldierRepo);
		RepoUtil.detachCommanderFromPlatoons(s, platoonRepo);
		RepoUtil.detachCommanderFromCompanies(s, companyRepo);
		RepoUtil.detachCommanderFromBattalions(s, battalionRepo);

		p.setCommander(s);
		platoonRepo.update(p);
	}

	@Override
	public String getName() {
		return "AssignCommanderToPlatoon";
	}

}
