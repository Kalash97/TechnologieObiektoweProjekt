package com.Actions;

import com.Entities.Team;
import com.Repos.TeamRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTeamAction implements Action{

	private View view;
	private TeamRepo repo;
	
	@Override
	public void launch() {
		
		view.print("Podaj id dru¿yny do usuniêcia.(s³owo <<cancel>> zawraca)");
		
		String line = view.read();
		if (line.equals("cancel")) {
			return;
		}
		
		Team t = repo.findById(Long.parseLong(line));
		
		repo.deleteTeam(t);
	}

	@Override
	public String getName() {
		return "DeleteTeam";
	}

}
