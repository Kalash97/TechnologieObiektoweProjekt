package com.controller.actions.PlatoonActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.repos.PlatoonRepo;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonByCommanderAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {

		view.print("Podaj imiê");
		String name = view.read();
		
		view.print("Podaj nazwisko");
		String lastName = view.read();
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for (Soldier s : soldiers) {
			List<Platoon> platoons = platoonRepo.findPlatoonOfCommander(s);
			Set<Platoon> platoonsSet = new HashSet<Platoon>();
			for(Platoon p : platoons) {
				platoonsSet.add(p);
			}
			ViewHelper.printResults(platoonsSet, view);
		}
	}

	@Override
	public String getName() {
		return "FindPlatoonByCommander";
	}

}
