package com.controller.actions.TeamActions;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.ValidUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTeamAction implements Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {
		Team t = new Team();
				
		String teamNumber = getTeamNumber();

		t.setNumber(Long.parseLong(teamNumber));
		
		repo.createTeam(t);
	}

	private String getTeamNumber() {
		String teamNumber;
		do{
			view.print("Podaj numer dru¿yny");
			teamNumber=view.read();
		}while(!ValidUtil.isLongInstance(teamNumber));
		return teamNumber;
	}

	@Override
	public String getName() {
		return "CreateTeam";
	}

}
