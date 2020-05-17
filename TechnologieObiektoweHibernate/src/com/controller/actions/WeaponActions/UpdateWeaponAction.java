package com.controller.actions.WeaponActions;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.ValidUtil;
import com.utils.enums.WeaponType;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateWeaponAction implements Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		Weapon w = getValidWeapon();

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
		if(!line.equals("")) {
			w.setWeaponType(WeaponType.valueOf(line.toUpperCase()));
		}
		
		repo.updateWeapon(w);
	}

	private Weapon getValidWeapon() {
		String line;
		Weapon w;
		do {
			do {
				view.print("Podaj id broni do modyfikacji.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			w = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(w));
		return w;
	}

	private String getWeaponType() {
		String weaponTypeStr;
		do {
			view.print("Podaj typ broni");
			weaponTypeStr=view.read();
			if(weaponTypeStr.equals("")) {
				break;
			}
		}while(!ValidUtil.isValid(weaponTypeStr, WeaponType.values()));
		return weaponTypeStr;
	}
	
	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling updateWeapon");
		}
	}

	@Override
	public String getName() {
		return "UpdateWeapon";
	}

}
