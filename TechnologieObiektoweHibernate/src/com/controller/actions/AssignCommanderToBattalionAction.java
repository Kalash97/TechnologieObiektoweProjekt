package com.controller.actions;

import com.model.entities.Battalion;
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
public class AssignCommanderToBattalionAction extends Action {

	private View view;
	private SoldierRepo soldierRepo;
	private BattalionRepo battalionRepo;
	private TeamRepo teamRepo;
	private PlatoonRepo platoonRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {
		Battalion b = RepoUtil.getValidBattalion(view, battalionRepo);
		Soldier s = RepoUtil.getValidSoldierWithMinimumRank(view, soldierRepo, Rank.LIEUTENANT_COLONEL);
		
		RepoUtil.detachCommanderFromTeams(s, teamRepo);
		RepoUtil.detachSoldierFromTeams(s, teamRepo, soldierRepo);
		RepoUtil.detachCommanderFromPlatoons(s, platoonRepo);
		RepoUtil.detachCommanderFromCompanies(s, companyRepo);
		RepoUtil.detachCommanderFromBattalions(s, battalionRepo);

		b.setCommander(s);
		battalionRepo.update(b);
	}

	

	@Override
	public String getName() {
		return "AssignCommanderToBattalion";
	}

}
