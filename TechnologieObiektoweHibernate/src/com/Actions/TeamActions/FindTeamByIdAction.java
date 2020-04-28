package com.Actions.TeamActions;

import java.util.List;

import com.Actions.Action;
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
		view.print("Dow�dca:: Imi�: " + t.getCommander().getName()+ ", Nazwisko: "+t.getCommander().getLastName()+", Stopie�: "+t.getCommander().getRank());
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
				view.print(i + ": " + "ID: " + list.get(i).getId()+ ", Imi�: "+list.get(i).getName() + ", Nazwisko: "+ list.get(i).getLastName() + ", Stopie�: "+ list.get(i).getRank());
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