package com.controller.actions.platoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.model.repos.TeamRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePlatoonAction implements Action {

	private View view;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {

		Platoon p = RepoUtil.getValidPlatoon(view, platoonRepo);
		
		RepoUtil.removeCommanderFromPlatoon(p, platoonRepo);
		RepoUtil.removeTeamsFromPlatoon(p, teamRepo, platoonRepo);
		
		platoonRepo.deletePlatoon(p);
	}

	
	
	

	@Override
	public String getName() {
		return "DeletePlatoon";
	}

}
