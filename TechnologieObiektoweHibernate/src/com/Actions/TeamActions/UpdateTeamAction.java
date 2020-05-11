package com.Actions.TeamActions;

import com.Actions.Action;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTeamAction implements Action{

	private View view;
	private TeamRepo repo;
	
	@Override
	public void launch() {
		Team t = findValidTeam();
		view.print("Podaj nowy numer.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		
		String line = getTeamNumber();
		if(!line.equals("")) {
			t.setNumber(Long.parseLong(line));
		}
		
		repo.updateTeam(t);
	}

	private Team findValidTeam() {
		String line;
		Team t;
		do {
			do {
				view.print("Podaj id dru¿yny do modyfikacji.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}

	private String getTeamNumber() {
		String teamNumber;
		do{
			view.print("Podaj numer dru¿yny");
			teamNumber=view.read();
			if(teamNumber.equals("")) {
				break;
			}
		}while(!ValidUtil.isLongInstance(teamNumber));
		return teamNumber;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling updateTeam");
		}
	}
	
	@Override
	public String getName() {
		return "UpdateTeam";
	}

}
