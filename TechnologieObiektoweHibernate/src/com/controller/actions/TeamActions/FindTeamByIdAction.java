package com.controller.actions.TeamActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Team;
import com.model.repos.TeamRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamByIdAction implements Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {
		Team t;
		t = findValidTeam();

		ViewHelper.printResults(Arrays.asList(t), view);
		view.print("");
		
		view.print("-----¯o³nierze:");
		ViewHelper.printResults(ViewHelper.soldiersToPersistable(t.getSoldiers()), view);	
		view.print("");
	}

	private Team findValidTeam() {
//		String line;
//		Team t;
//		do {
//			do {
//				view.print("Podaj id dru¿yny do znalezienia.(s³owo <<cancel>> zawraca)");
//				line = view.read();
//				canceling(line);
//			} while (!ValidUtil.isLongInstance(line));
//			t = repo.findById(Long.parseLong(line));
//		} while (!ValidUtil.isValid(t));
//		return t;

		while (true) {
			long id = view.getValidNumberCancellable("Podaj ID dru¿yny");
			Team t = repo.findById(id);
			if(t!=null) {
				return t;
			}
		}
	}

//	private void canceling(String line) {
//		if ("cancel".equals(line)) {
//			throw new OperationCancelException("canceling findTeam");
//		}
//	}

	@Override
	public String getName() {
		return "FindTeamById";
	}

}
