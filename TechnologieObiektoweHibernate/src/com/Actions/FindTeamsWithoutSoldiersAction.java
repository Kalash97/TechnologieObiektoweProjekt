package com.Actions;

import java.util.List;

import com.Entities.Team;
import com.Repos.TeamRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamsWithoutSoldiersAction implements Action{

	private View view;
	private TeamRepo teamRepo;
	
	@Override
	public void launch() {
		List<Team> teams = teamRepo.findTeamsWithoutSoldiers();
		showTeams(teams);
	}

	private void showTeams(List<Team> teams) {
		if(teams.size()>0) {
			for(int i=0; i<teams.size();i++) {
				Team t = teams.get(i);
				view.print(i + ": ID: "+t.getId()+", Numer: "+t.getNumber());
			}
		}else {
			view.print("Brak dru�yn bez �o�nierzy");
		}
	}

	@Override
	public String getName() {
		return "FindTeamsWithoutSoldiers";
	}

}
