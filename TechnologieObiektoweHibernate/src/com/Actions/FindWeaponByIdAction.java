package com.Actions;

import com.Entities.Weapon;
import com.Exceptions.OperationCancelException;
import com.Repos.WeaponRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindWeaponByIdAction implements Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		Weapon w;
		w = findValidWeapon();
		
		view.print("Znaleziona broñ:");
		view.print("Nazwa: "+w.getName());
		view.print("Numer seryjny: "+w.getSerialNumber());
		view.print("Typ: "+w.getWeaponType());
		view.print("W³aœciciel: "+w.getSoldier());
		view.print("");
	}

	private Weapon findValidWeapon() {
		String line;
		Weapon w;
		do {
			do {
				view.print("Podaj id broni do znalezienia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			w = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(w));
		return w;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling findWeapon");
		}
	}
	
	@Override
	public String getName() {
		return "FindWeaponById";
	}

}
