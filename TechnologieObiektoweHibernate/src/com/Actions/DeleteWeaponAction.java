package com.Actions;

import com.Entities.Weapon;
import com.Repos.WeaponRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteWeaponAction implements Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		view.print("Podaj id broni do usuniêcia.(s³owo <<cancel>> zawraca)");

		String line = view.read();
		if (line.equals("cancel")) {
			return;
		}

		Weapon w = repo.findById(Long.parseLong(line));
		
		repo.deleteWeapon(w);
	}

	@Override
	public String getName() {
		return "DeleteWeapon";
	}

}
