package com.controller.actions.weaponActions;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.RepoUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteWeaponAction extends Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		Weapon w = RepoUtil.getValidWeapon(view, repo);

		repo.delete(w);
	}
	
	@Override
	public String getName() {
		return "DeleteWeapon";
	}

}
