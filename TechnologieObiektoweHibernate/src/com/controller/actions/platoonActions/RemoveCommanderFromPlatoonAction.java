package com.controller.actions.platoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromPlatoonAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = RepoUtil.getValidPlatoon(view, repo);
		
		p.setCommander(null);
		repo.updatePlatoon(p);
	}

	@Override
	public String getName() {
		return "RemoveCommanderFromPlatoon";
	}

}
