package com.controller.actions.teamActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.controller.actions.Action;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamByCommanderAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private TeamRepo teamRepo;
	
	@Override
	public void launch() {
		
		view.print("Podaj imiê");
		String name = view.read();
		
		view.print("Podaj nazwisko");
		String lastName = view.read();
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for(Soldier s : soldiers) {
			List<Team> teams = teamRepo.findTeamsOfCommander(s);
			Set<Team> teamsSet = new HashSet<Team>();
			for(Team t : teams) {
				teamsSet.add(t);
			}
			ViewHelper.printResults(teamsSet, view);
		}
	}

	@Override
	public String getName() {
		return "FindTeamByCommander";
	}

}
