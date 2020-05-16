package com.Actions.PlatoonActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Actions.Action;
import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Repos.SoldierRepo;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonByCommanderAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {

		view.print("Podaj imiê");
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
