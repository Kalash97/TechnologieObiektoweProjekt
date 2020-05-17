package com.controller.actions.WeaponActions;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.ValidUtil;
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
		
		String weaponTypeStr = getWeaponType();
		
		w.setWeaponType(WeaponType.valueOf(weaponTypeStr.toUpperCase()));
		
		repo.createWeapon(w);
	}

	private String getWeaponType() {
		String weaponTypeStr;
		do {
			view.print("Podaj typ broni");
			weaponTypeStr=view.read();
			
		}while(!ValidUtil.isValid(weaponTypeStr, WeaponType.values()));
		return weaponTypeStr;
	}

	@Override
	public String getName() {
		return "CreateWeapon";
	}

}
