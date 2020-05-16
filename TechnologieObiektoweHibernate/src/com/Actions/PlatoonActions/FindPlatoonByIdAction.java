package com.Actions.PlatoonActions;

import java.util.Arrays;

import com.Actions.Action;
import com.Entities.Platoon;
import com.Repos.PlatoonRepo;
import com.Utils.ViewHelper;
import com.View.View;

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
		ViewHelper.printResults(ViewHelper.teamsToPersistable(p.getTeams()), view);		
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
