package com.controller.actions.platoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatePlatoonAction extends Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = RepoUtil.getValidPlatoon(view, repo);
		p.setNumber(view.getValidNumber("Podaj Id"));
		
		repo.update(p);
	}
	
	@Override
	public String getName() {
		return "UpdatePlatoon";
	}

}
