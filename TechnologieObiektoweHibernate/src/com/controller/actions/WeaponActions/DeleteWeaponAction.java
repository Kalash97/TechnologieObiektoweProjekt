package com.controller.actions.WeaponActions;

import com.controller.actions.Action;
import com.model.entities.Weapon;
import com.model.repos.WeaponRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteWeaponAction implements Action {

	private View view;
	private WeaponRepo repo;

	@Override
	public void launch() {

		Weapon w;

		w = getValidWeapon();

		repo.deleteWeapon(w);
	}

	private Weapon getValidWeapon() {
		String line;
		Weapon w;
		do {
			do {
				view.print("Podaj id broni do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			w = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(w));
		return w;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling findSoldier");
		}
	}
	
	@Override
	public String getName() {
		return "DeleteWeapon";
	}

}
