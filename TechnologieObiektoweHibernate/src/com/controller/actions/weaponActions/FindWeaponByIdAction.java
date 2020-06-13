package com.controller.actions.weaponActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.RepoUtil;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindWeaponByIdAction extends Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		Weapon w = RepoUtil.getValidWeapon(view, repo);

		ViewHelper.printResults(Arrays.asList(w), view);
		try {
			ViewHelper.printResults(Arrays.asList(w.getSoldier()), view);
		}catch (NullPointerException e) {
			view.print("Brak ¿o³nierza");
		}
	}

	@Override
	public String getName() {
		return "FindWeaponById";
	}

}
