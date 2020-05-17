package com.controller.actions.PlatoonActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonByCommanderAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {

		view.print("Podaj imi�");
		String name = view.read();
		
		view.print("Podaj nazwisko");
		String lastName = view.read();
		
		List<Soldier> soldiers = repo.findSoldiersByName(name, lastName);
		
		for (Soldier s : soldiers) {
			List<Platoon> platoons = repo.findPlatoonOfCommander(s);
			Set<Platoon> platoonsSet = new HashSet<Platoon>();
			for(Platoon p : platoons) {
				platoonsSet.add(p);
			}
			ViewHelper.printResults(ViewHelper.platoonsToPersistable(platoonsSet), view);
		}
	}

	@Override
	public String getName() {
		return "FindPlatoonByCommander";
	}

}