package com.Actions;

import java.util.List;

import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTeamByIdAction implements Action {

	private View view;
	private TeamRepo repo;

	@Override
	public void launch() {
		Team t;
		t = findValidTeam();

		view.print("Znaleziona dru�yna:");
		view.print("Numer: " + t.getNumber());
		view.print("Dow�dca: " + t.getCommander());
		Platoon p = t.getPlatoon();
		if (p!=null) {
			view.print("Pluton: Id:" + p.getId() + " Numer: " + p.getNumber());
		}else {
			view.print("Pluton: "+ p);
		}
		view.print("�o�nierze w dru�ynie:");
		List<Soldier> list = t.getSoldiers();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				view.print("�o�nierz nr.: " + i + " ID: " + list.get(i).getId());
			}
		}
		view.print("");
	}

	private Team findValidTeam() {
		String line;
		Team t;
		do {
			do {
				view.print("Podaj id dru�yny do znalezienia.(s�owo <<cancel>> zawraca)");
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
		return "FindTeamById";
	}

}
