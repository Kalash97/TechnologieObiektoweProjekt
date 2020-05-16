package com.Actions.SoldierActions;

import java.util.Arrays;

import com.Actions.Action;
import com.Entities.Soldier;
import com.Repos.SoldierRepo;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldierByIdAction implements Action {

	private View view;
	private SoldierRepo repo;

	@Override
	public void launch() {
		Soldier s;

		s = findValidSoldier();
		ViewHelper.printResults(Arrays.asList(s), view);
		
		try {
			ViewHelper.printResults(Arrays.asList(s.getTeam()), view);
		} catch (NullPointerException e) {
			view.print("Brak dru¿yny");
		}
		
	}

	private Soldier findValidSoldier() {

		while (true) {
			long id = view.getValidNumberCancellable("Podaj ID ¿o³nierza");
			Soldier s = repo.findById(id);
			if (s != null) {
				return s;
			}
		}
	}

	@Override
	public String getName() {
		return "FindSoldierById";
	}

}
