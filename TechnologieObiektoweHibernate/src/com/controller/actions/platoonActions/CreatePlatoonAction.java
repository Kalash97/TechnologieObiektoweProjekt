package com.controller.actions.platoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlatoonAction extends Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = new Platoon();		
		p.setNumber(view.getValidNumber("podaj Id"));
		
		repo.create(p);
	}

	@Override
	public String getName() {
		return "CreatePlatoon";
	}

}
