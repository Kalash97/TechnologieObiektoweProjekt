package com.Actions;

import com.Entities.Weapon;
import com.Repos.WeaponRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteWeaponAction implements Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		String line;
		Weapon w;

		do {
			do {
				view.print("Podaj id broni do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			w = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(w));

		repo.deleteWeapon(w);
	}

	@Override
	public String getName() {
		return "DeleteWeapon";
	}

}
