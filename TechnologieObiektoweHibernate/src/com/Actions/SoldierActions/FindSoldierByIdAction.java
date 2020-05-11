package com.Actions.SoldierActions;

import java.util.Arrays;
import java.util.List;

import com.Actions.Action;
import com.Entities.Soldier;
import com.Entities.Team;
import com.Entities.Weapon;
import com.Exceptions.OperationCancelException;
import com.Repos.SoldierRepo;
import com.Utils.ValidUtil;
import com.Utils.ViewHelper;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldierByIdAction implements Action {

	private View view;
	private SoldierRepo repo;

	@Override
	public void launch() {
		Soldier s;

		s = findValidSoldier();

//		view.print("Znaleziony ¿o³nierz:");
//		view.print("Imiê: " + s.getName());
//		view.print("Nazwisko: " + s.getLastName());
//		view.print("Grupa krwi: " + s.getBloodType());
//		view.print("Stopieñ: " + s.getRank());
//		view.print("Posiadana broñ:");
//		List<Weapon> list = s.getWeapons();
//		if (list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				view.print(i + ": " + " ID: " + list.get(i).getId() + ", Nazwa: "+ list.get(i).getName()+ ", Nr. seryjny: "+ list.get(i).getSerialNumber());
//			}
//		}
//		view.print("Dru¿yna:: ID: " + s.getTeam().getId()+ ", Numer: "+s.getTeam().getNumber());
//		view.print("");

		ViewHelper.printResults(Arrays.asList(s), view);
		try {
			ViewHelper.printResults(Arrays.asList(s.getTeam()), view);
		} catch (NullPointerException e) {
			view.print("Brak dru¿yny");
		}
		
	}

	private Soldier findValidSoldier() {
//		String line;
//		Soldier s;
//		do {
//			do {
//				view.print("Podaj id ¿o³nierza do znalezienia.");
//				line = view.read();	
//				canceling(line);
//			} while (!ValidUtil.isLongInstance(line));
//			s = repo.findById(Long.parseLong(line));
//		} while (!ValidUtil.isValid(s));
//		return s;

		while (true) {
			long id = view.getValidNumberCancellable("Podaj ID ¿o³nierza");
			Soldier s = repo.findById(id);
			if (s != null) {
				return s;
			}
		}
	}

	@Override
	public String getName() {
		return "FindSoldierById";
	}

}
