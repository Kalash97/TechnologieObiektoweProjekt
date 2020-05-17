package com.controller.actions.TeamActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamsWithoutPlatoonAction implements Action{

	private View view;
	private TeamRepo teamRepo;
	
	@Override
	public void launch() {
		List<Team> teams = teamRepo.findTeamsWithoutPlatoon();
		//showTeams(teams);
		ViewHelper.printResults(ViewHelper.teamsToPersistable(teams), view);
	}

//	private void showTeams(List<Team> teams) {
//		if(teams.size()>0) {
//			for(int i=0; i<teams.size();i++) {
//				Team t = teams.get(i);
//				view.print(i+": ID: "+ t.getId()+", Numer: "+t.getNumber());
//			}
//		}else {
//			view.print("Brak nieprzypisanch dru¿yn.");
//		}
//	}

	@Override
	public String getName() {
		return "FindTeamsWithoutPlatoon";
	}

}
