package com.Actions.WeaponActions;

import com.Actions.Action;
import com.Entities.Weapon;
import com.Enums.WeaponType;
import com.Repos.WeaponRepo;
import com.Utils.ValidUtil;
import com.View.View;

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
