package com.controller.actions;

import com.model.entities.Company;
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
public class AssignCommanderToCompanyAction extends Action {

	private View view;
	private SoldierRepo soldierRepo;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {
		Company c = RepoUtil.getValidCompany(view, companyRepo);
		Soldier s = RepoUtil.getValidSoldierWithMinimumRank(view, soldierRepo, Rank.CAPITAN);

		RepoUtil.detachCommanderFromTeams(s, teamRepo);
		RepoUtil.detachSoldierFromTeams(s, teamRepo, soldierRepo);
		RepoUtil.detachCommanderFromPlatoons(s, platoonRepo);
		RepoUtil.detachCommanderFromCompanies(s, companyRepo);
		RepoUtil.detachCommanderFromBattalions(s, battalionRepo);

		c.setCommander(s);
		companyRepo.update(c);
	}

	@Override
	public String getName() {
		return "AssignCommanderToCompany";
	}

}
