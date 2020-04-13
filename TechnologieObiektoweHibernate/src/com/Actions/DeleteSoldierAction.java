package com.Actions;

import com.Entities.Soldier;
import com.Repos.SoldierRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSoldierAction implements Action {

	private View view;
	private SoldierRepo repo;

	@Override
	public void launch() {

		String line;
		Soldier s;

		do {
			do {
				view.print("Podaj id ¿o³nierza do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			s = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));

		repo.deleteSoldier(s);
	}

	@Override
	public String getName() {
		return "DeleteSoldier";
	}

}
