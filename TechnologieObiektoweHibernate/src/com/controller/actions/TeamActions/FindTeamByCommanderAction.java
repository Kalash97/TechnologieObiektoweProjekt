package com.controller.actions.TeamActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.repos.SoldierRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamByCommanderAction implements Action{

	private View view;
	private SoldierRepo repo;
	
	@Override
	public void launch() {
		
		view.print("Podaj imi�");
		String name = view.read();
		
		view.print("Podaj nazwisko");
		String lastName = view.read();
		
		List<Soldier> soldiers = repo.findSoldiersByName(name, lastName);
		
		for(Soldier s : soldiers) {
			List<Team> teams = repo.findTeamsOfCommander(s);
			Set<Team> teamsSet = new HashSet<Team>();
			for(Team t : teams) {
				teamsSet.add(t);
			}
			ViewHelper.printResults(ViewHelper.teamsToPersistable(teamsSet), view);
		}
	}

	@Override
	public String getName() {
		return "FindTeamByCommander";
	}

}
