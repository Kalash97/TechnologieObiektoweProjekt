package com.controller.actions.weaponActions;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.RepoUtil;
import com.utils.enums.WeaponType;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateWeaponAction extends Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {
		Weapon w = new Weapon();

		w.setName(view.readProperty("Podaj nazw�"));

		w.setSerialNumber(view.readProperty("Podaj ne. seryjny"));

		view.print("Dost�pne typy broni");
		view.print(WeaponType.values());
		w.setWeaponType(RepoUtil.getValidWeaponType(view));
		
		repo.create(w);
	}

	@Override
	public String getName() {
		return "CreateWeapon";
	}

}
