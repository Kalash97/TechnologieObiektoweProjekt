package com.Actions.BattalionActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Actions.Action;
import com.Entities.Battalion;
import com.Entities.Soldier;
import com.Repos.SoldierRepo;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionByCommanderAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {
		view.print("Podaj imiê");
		String name = view.read();
		
		view.print("Podaj nazwisko");
		String lastName = view.read();
		
		List<Soldier> soldiers = repo.findSoldiersByName(name, lastName);
		
		for(Soldier s : soldiers) {
			List<Battalion> battalions = repo.findBattalionOfCommander(s);
			Set<Battalion> battalionsSet = new HashSet<Battalion>();
			for(Battalion b : battalions) {
				battalionsSet.add(b);
			}
			ViewHelper.printResults(ViewHelper.battalionsToPersistable(battalionsSet), view);
		}
	}

	@Override
	public String getName() {
		return "FindBattalionByCommander";
	}

}
