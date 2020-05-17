package com.controller.actions.BattalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.ValidUtil;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBattalionAction implements Action{
	
	private View view;
	private BattalionRepo repo;

	@Override
	public void launch() {
		Battalion b = new Battalion();
		
		 String battalionNumber = readBattalionNumber();
		
		b.setNumber(Long.parseLong(battalionNumber));
		
		repo.createBattalion(b);
	}

	private String readBattalionNumber() {
		String battalionNumber;
		do {
			view.print("Podaj numer batalionu");
			battalionNumber=view.read();
		}while(!ValidUtil.isLongInstance(battalionNumber));
		return battalionNumber;
	}

	@Override
	public String getName() {
		return "CreateBattalion";
	}

}
