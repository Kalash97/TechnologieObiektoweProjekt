package com.Actions;

import com.Entities.Platoon;
import com.Repos.PlatoonRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePlatoonAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		System.out.println("Podaj id plutonu do usuniêcia.(s³owo <<cancel>> zawraca)");
		
		String line = view.read();
		if (line.equals("cancel")) {
			return;
		}
		
		Platoon p =repo.findById(Long.parseLong(line));
		
		repo.deletePlatoon(p);
	}

	@Override
	public String getName() {
		return "DeletePlatoon";
	}

}
