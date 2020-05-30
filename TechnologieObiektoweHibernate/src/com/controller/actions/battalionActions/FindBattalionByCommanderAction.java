package com.controller.actions.battalionActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.entities.Soldier;
import com.model.repos.BattalionRepo;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionByCommanderAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private BattalionRepo battalionRepo;
	
	@Override
	public void launch() {
		String name = view.readProperty("Podaj imiê");	
		String lastName = view.readProperty("Podaj nazwisko");
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for(Soldier s : soldiers) {
			List<Battalion> battalions = battalionRepo.findBattalionOfCommander(s);
			ViewHelper.printResults(s.getFullName(), battalions, view);
		}
	}

	@Override
	public String getName() {
		return "FindBattalionByCommander";
	}

}
