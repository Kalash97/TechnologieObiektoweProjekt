package com.controller.actions.teamActions;

import java.util.List;

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
		
		String name = view.readProperty("Podaj imiê");
		
		String lastName = view.readProperty("Podaj nazwisko");
		
		List<Soldier> soldiers = soldierRepo.findSoldiersByName(name, lastName);
		
		for(Soldier s : soldiers) {
			List<Team> teams = teamRepo.findTeamsOfCommander(s);
			ViewHelper.printResults(s.getFullName(), teams, view);
		}
	}

	@Override
	public String getName() {
		return "FindTeamByCommander";
	}

}
