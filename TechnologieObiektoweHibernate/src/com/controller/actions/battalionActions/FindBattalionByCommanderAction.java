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
		view.print("Podaj imiê");
		String name = view.read();
		
		view.print("Podaj nazwisko");
		String lastName = view.read();
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for(Soldier s : soldiers) {
			List<Battalion> battalions = battalionRepo.findBattalionOfCommander(s);
			Set<Battalion> battalionsSet = new HashSet<Battalion>();
			for(Battalion b : battalions) {
				battalionsSet.add(b);
			}
			ViewHelper.printResults(battalionsSet, view);
		}
	}

	@Override
	public String getName() {
		return "FindBattalionByCommander";
	}

}
