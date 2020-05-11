package com.Actions.TeamActions;

import java.util.Arrays;
import java.util.List;

import com.Actions.Action;
import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.Utils.ViewHelper;
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

//		view.print("Znaleziona dru¿yna:");
//		view.print("Numer: " + t.getNumber());
//		view.print("Dowódca:: Imiê: " + t.getCommander().getName() + ", Nazwisko: " + t.getCommander().getLastName()
//				+ ", Stopieñ: " + t.getCommander().getRank());
//		Platoon p = t.getPlatoon();
//		if (p != null) {
//			view.print("Pluton: Id:" + p.getId() + " Numer: " + p.getNumber());
//		} else {
//			view.print("Pluton: " + p);
//		}
//		view.print("¯o³nierze w dru¿ynie:");
//		List<Soldier> list = t.getSoldiers();
//		if (list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				view.print(i + ": " + "ID: " + list.get(i).getId() + ", Imiê: " + list.get(i).getName() + ", Nazwisko: "
//						+ list.get(i).getLastName() + ", Stopieñ: " + list.get(i).getRank());
//			}
//		}
//		view.print("");
		
		ViewHelper.printResults(Arrays.asList(t), view);
		try {
			view.print("-----Dowódca:");
			ViewHelper.printResults(Arrays.asList(t.getCommander()), view);
		}catch (NullPointerException e) {
			view.print("Brak dowódcy");
		}
		
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

	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling findTeam");
		}
	}

	@Override
	public String getName() {
		return "FindTeamById";
	}

}
