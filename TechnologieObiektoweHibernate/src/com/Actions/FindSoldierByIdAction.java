package com.Actions;

import java.util.List;

import com.Entities.Soldier;
import com.Entities.Weapon;
import com.Exceptions.OperationCancelException;
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
		Soldier s;
		
		s = findValidSoldier();

		view.print("Znaleziony ¿o³nierz:");
		view.print("Imiê: " + s.getName());
		view.print("Nazwisko: " + s.getLastName());
		view.print("Grupa krwi: " + s.getBloodType());
		view.print("Stopieñ: " + s.getRank());
		view.print("Posiadana broñ:");
		List<Weapon> list = s.getWeapons();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				view.print("Broñ nr.:" + i + " ID: " + list.get(i).getId());
			}
		}
		view.print("Dru¿yna: " + s.getTeam());
		view.print("");
	}

	private Soldier findValidSoldier() {
		String line;
		Soldier s;
		do {
			do {
				view.print("Podaj id ¿o³nierza do znalezienia.");
				line = view.read();	
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling findSoldier");
		}
	}

	@Override
	public String getName() {
		return "FindSoldierById";
	}

}
