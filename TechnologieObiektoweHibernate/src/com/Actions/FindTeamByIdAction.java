package com.Actions;

import java.util.List;

import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Entities.Team;
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
		String line;
		Team t;
		do {
			do {
				view.print("Podaj id dru¿yny do znalezienia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			t = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));

		view.print("Znaleziona dru¿yna:");
		view.print("Numer: " + t.getNumber());
		view.print("Dowódca: " + t.getCommander());
		Platoon p = t.getPlatoon();
		if (p!=null) {
			view.print("Pluton: Id:" + p.getId() + " Numer: " + p.getNumber());
		}else {
			view.print("Pluton: "+ p);
		}
		view.print("¯o³nierze w dru¿ynie:");
		List<Soldier> list = t.getSoldiers();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				view.print("¯o³nierz nr.: " + i + " ID: " + list.get(i).getId());
			}
		}
		view.print("");
	}

	@Override
	public String getName() {
		return "FindTeamById";
	}

}
