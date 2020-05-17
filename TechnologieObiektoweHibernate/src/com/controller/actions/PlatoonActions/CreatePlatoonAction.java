package com.controller.actions.PlatoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.ValidUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlatoonAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = new Platoon();

		String platoonNumber = getPlatoonNumber();
		
		p.setNumber(Long.parseLong(platoonNumber));
		
		repo.createPlatoon(p);
	}

	private String getPlatoonNumber() {
		String platoonNumber;
		do {
			view.print("Podaj numer plutonu");
			platoonNumber=view.read();
		}while(!ValidUtil.isLongInstance(platoonNumber));
		return platoonNumber;
	}

	@Override
	public String getName() {
		return "CreatePlatoon";
	}

}
