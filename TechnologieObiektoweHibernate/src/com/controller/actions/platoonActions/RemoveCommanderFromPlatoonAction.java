package com.controller.actions.platoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromPlatoonAction extends Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = RepoUtil.getValidPlatoon(view, repo);
		
		RepoUtil.removeCommanderFromPlatoon(p, repo);
	}

	@Override
	public String getName() {
		return "RemoveCommanderFromPlatoon";
	}

}
