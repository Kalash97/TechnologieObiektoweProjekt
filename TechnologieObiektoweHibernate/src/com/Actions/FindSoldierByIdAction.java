package com.Actions;

import java.util.List;

import com.Entities.Soldier;
import com.Entities.Weapon;
import com.Repos.SoldierRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindSoldierByIdAction implements Action {

	private View view;
	private SoldierRepo repo;

	@Override
	public void launch() {
		String line;
		Soldier s;
		do {
			do {
				view.print("Podaj id �o�nierza do znalezienia.(s�owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			s = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));

		view.print("Znaleziony �o�nierz:");
		view.print("Imi�: " + s.getName());
		view.print("Nazwisko: " + s.getLastName());
		view.print("Grupa krwi: " + s.getBloodType());
		view.print("Stopie�: " + s.getRank());
		view.print("Posiadana bro�:");
		List<Weapon> list = s.getWeapons();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				view.print("Bro� nr.:" + i + " ID: " + list.get(i).getId());
			}
		}
		view.print("Dru�yna: " + s.getTeam());
		view.print("");
	}

	@Override
	public String getName() {
		return "FindSoldierById";
	}

}
