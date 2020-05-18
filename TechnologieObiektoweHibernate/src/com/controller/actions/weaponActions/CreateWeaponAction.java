package com.controller.actions.weaponActions;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.RepoUtil;
import com.utils.enums.WeaponType;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateWeaponAction implements Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {
		Weapon w = new Weapon();

		view.print("Podaj nazwê");
		w.setName(view.read());

		view.print("Podaj nr. seryjny");
		w.setSerialNumber(view.read());

		view.print("Dostêpne typy broni");
		view.print(WeaponType.values());
		w.setWeaponType(RepoUtil.getValidWeaponType(view));
		
		repo.createWeapon(w);
	}

	@Override
	public String getName() {
		return "CreateWeapon";
	}

}
