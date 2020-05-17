package com.controller.actions.PlatoonActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonByIdAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p;
		p = findValidPlatoon();
		
		ViewHelper.printResults(Arrays.asList(p), view);
		view.print("");
		
		try {
			view.print("-----Kompania:");
			ViewHelper.printResults(Arrays.asList(p.getCompany()), view);
		}catch (NullPointerException e) {
			view.print("Brak kompanii");
		}
		view.print("");
		
		view.print("-----Dru¿yny:");
		ViewHelper.printResults(p.getTeams(), view);		
		view.print("");
	}

	private Platoon findValidPlatoon() {

		while(true) {
			long id = view.getValidNumberCancellable("Podaj ID plutonu");
			Platoon p = repo.findById(id);
			if(p!=null) {
				return p;
			}
		}
	}
	
	@Override
	public String getName() {
		return "FindPlatoonById";
	}

}
