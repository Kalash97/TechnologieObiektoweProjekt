package com.Actions;

import com.Entities.Platoon;
import com.Repos.PlatoonRepo;
import com.Utils.ValidUtil;
import com.View.View;

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
