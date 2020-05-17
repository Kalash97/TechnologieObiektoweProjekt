package com.controller.actions.PlatoonActions;

import com.controller.actions.Action;
import com.model.entities.Platoon;
import com.model.repos.PlatoonRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatePlatoonAction implements Action{

	private View view;
	private PlatoonRepo repo;
	
	@Override
	public void launch() {
		Platoon p = findValidPlatoon();
		view.print("Podaj nowy numer.(Zostaw puste je�li nie chcesz zmienia�)");
		String line = getPlatoonNumber();
		if(!line.equals("")) {
			p.setNumber(Long.parseLong(line));
		}
		
		repo.updatePlatoon(p);
	}

	private Platoon findValidPlatoon() {
		String line;
		Platoon p;
		do {
			do {
				view.print("Podaj id plutonu do modyfikacji.(s�owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			p = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(p));
		return p;
	}

	private String getPlatoonNumber() {
		String platoonNumber;
		do {
			view.print("Podaj numer plutonu");
			platoonNumber=view.read();
			if(platoonNumber.equals("")) {
				break;
			}
		}while(!ValidUtil.isLongInstance(platoonNumber));
		return platoonNumber;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling updatePlatoon");
		}
	}
	
	@Override
	public String getName() {
		return "UpdatePlatoon";
	}

}
