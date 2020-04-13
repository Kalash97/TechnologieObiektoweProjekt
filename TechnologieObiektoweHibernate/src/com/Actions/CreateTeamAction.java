package com.Actions;

import com.Entities.Team;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTeamAction implements Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {
		Team t = new Team();
				
		String teamNumber;
		do{
			view.print("Podaj numer dru¿yny");
			teamNumber=view.read();
		}while(!ValidUtil.isValid(teamNumber));

		t.setNumber(Long.parseLong(teamNumber));
		
		repo.createTeam(t);
	}

	@Override
	public String getName() {
		return "CreateTeam";
	}

}
