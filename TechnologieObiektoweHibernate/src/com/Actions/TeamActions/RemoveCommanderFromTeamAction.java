package com.Actions.TeamActions;

import com.Actions.Action;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromTeamAction implements Action{

	private View view;
	private TeamRepo repo;
	
	@Override
	public void launch() {
		Team t = findValidTeam();
		
		t.setCommander(null);
		repo.updateTeam(t);
	}

	private Team findValidTeam() {
		String line;
		Team t;
		do {
			do {
				view.print("Podaj id dru¿yny do usuniêcia dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling findTeam");
		}
	}
	
	@Override
	public String getName() {
		return "RemoveCommanderFromTeam";
	}

}
