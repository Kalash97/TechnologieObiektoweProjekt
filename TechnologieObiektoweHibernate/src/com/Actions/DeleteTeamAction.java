package com.Actions;

import com.Entities.Team;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTeamAction implements Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {

		String line;
		Team t;

		do {
			do {
				view.print("Podaj id dru¿yny do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			t = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		repo.deleteTeam(t);
	}

	@Override
	public String getName() {
		return "DeleteTeam";
	}

}
