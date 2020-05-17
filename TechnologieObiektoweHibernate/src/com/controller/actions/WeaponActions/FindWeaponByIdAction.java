package com.controller.actions.WeaponActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindWeaponByIdAction implements Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		Weapon w;
		w = findValidWeapon();

		ViewHelper.printResults(Arrays.asList(w), view);
		try {
			ViewHelper.printResults(Arrays.asList(w.getSoldier()), view);
		}catch (NullPointerException e) {
			view.print("Brak ¿o³nierza");
		}
	}

	private Weapon findValidWeapon() {
		while (true) {
			long id = view.getValidNumberCancellable("Podaj ID broni");
			Weapon w = repo.findById(id);
			if(w!=null) {
				return w;
			}
		}
	}

	@Override
	public String getName() {
		return "FindWeaponById";
	}

}
