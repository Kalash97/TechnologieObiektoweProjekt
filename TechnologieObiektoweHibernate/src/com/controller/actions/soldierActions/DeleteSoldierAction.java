package com.controller.actions.soldierActions;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.model.repos.WeaponRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSoldierAction implements Action {

	private View view;
	private SoldierRepo soldierRepo;
	private WeaponRepo weaponRepo;
	private TeamRepo teamRepo;
	private PlatoonRepo platoonRepo;
	private CompanyRepo companyRepo;
	private BattalionRepo battalionRepo;

	@Override
	public void launch() {

		Soldier s = RepoUtil.getValidSoldier(view, soldierRepo);
		RepoUtil.detachWeaponsFromSoldier(s, weaponRepo, soldierRepo);
		RepoUtil.detachSoldierFromTeams(s, teamRepo, soldierRepo);
		RepoUtil.detachCommanderFromTeams(s, teamRepo);
		RepoUtil.detachCommanderFromPlatoons(s, platoonRepo);
		RepoUtil.detachCommanderFromCompanies(s, companyRepo);
		RepoUtil.detachCommanderFromBattalions(s, battalionRepo);
		
		soldierRepo.deleteSoldier(s);
	}


	@Override
	public String getName() {
		return "DeleteSoldier";
	}

}
