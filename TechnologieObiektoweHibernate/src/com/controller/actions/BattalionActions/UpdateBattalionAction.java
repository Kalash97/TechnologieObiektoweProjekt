package com.controller.actions.BattalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateBattalionAction implements Action{

	private View view;
	private BattalionRepo repo;
	
	@Override
	public void launch() {
		Battalion b = getValidBattalion();
		view.print("Podaj nowy numer.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		String line = getBattalionNumber();
		if(!line.equals("")) {
			b.setNumber(Long.parseLong(line));
		}
		
		repo.updateBattalion(b);
	}

	private Battalion getValidBattalion() {
		String line;
		Battalion b;
		do {
			do {
				view.print("Podaj id batalionu do modyfikacji.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
	}

	private String getBattalionNumber() {
		String battalionNumber;
		do {
			view.print("Podaj numer batalionu");
			battalionNumber=view.read();
			if(battalionNumber.equals("")) {
				break;
			}
		}while(!ValidUtil.isLongInstance(battalionNumber));
		return battalionNumber;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling updateBattalion");
		}
	}
	
	@Override
	public String getName() {
		return "UpdateBattalion";
	}

}
