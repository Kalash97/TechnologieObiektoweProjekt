package com.Actions;

import com.Entities.Soldier;
import com.Repos.SoldierRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSoldierAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {
		
		view.print("Podaj id ¿o³nierza do usuniêcia.(s³owo <<cancel>> zawraca)");
		
		String line = view.read();
		if(line.equals("cancel")) {
			return;
		}
		
		Soldier s = repo.findById(Long.parseLong(line));
		
		repo.deleteSoldier(s);
	}

	@Override
	public String getName() {
		return "DeleteSoldier";
	}

}
