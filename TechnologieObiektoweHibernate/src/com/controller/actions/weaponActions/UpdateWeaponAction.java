package com.controller.actions.weaponActions;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.RepoUtil;
import com.utils.enums.WeaponType;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateWeaponAction extends Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		Weapon w = RepoUtil.getValidWeapon(view, repo);

		view.print("Podaj now¹ nazwê.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		String line = view.read();
		if (!line.equals("")) {
			w.setName(line);
		}

		view.print("Podaj nowy nr. seryjny.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		line = view.read();
		if (!line.equals("")) {
			w.setSerialNumber(line);
		}

		view.print("Podaj nowy typ.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		view.print("Dostêpne typy broni");
		view.print(WeaponType.values());
		line = getWeaponType();
		if (!line.equals("")) {
			w.setWeaponType(WeaponType.valueOf(line.toUpperCase()));
		}

		repo.update(w);
	}

	private String getWeaponType() {
		while (true) {
			try {
				String weaponType = view.read();
				return ("".equals(weaponType) || WeaponType.valueOf(weaponType) instanceof WeaponType) ? weaponType
						: "";
			} catch (Exception e) {

			}
		}
	}

	@Override
	public String getName() {
		return "UpdateWeapon";
	}
}
