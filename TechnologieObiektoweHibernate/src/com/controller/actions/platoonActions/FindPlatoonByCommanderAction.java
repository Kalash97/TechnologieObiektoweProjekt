package com.controller.actions.platoonActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.repos.PlatoonRepo;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPlatoonByCommanderAction extends Action{

	private View view;
	private SoldierRepo soldierRepo;
	private PlatoonRepo platoonRepo;
	
	@Override
	public void launch() {

		String name = view.readProperty("Podaj imiê");
		String lastName = view.readProperty("Podaj nazwisko");
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for (Soldier s : soldiers) {
			List<Platoon> platoons = platoonRepo.findPlatoonOfCommander(s);
			ViewHelper.printResults(s.getFullName(),platoons, view);
		}
	}

	@Override
	public String getName() {
		return "FindPlatoonByCommander";
	}

}
