package com.controller.actions.battalionActions;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBattalionAction implements Action{
	
	private View view;
	private BattalionRepo repo;

	@Override
	public void launch() {
		Battalion b = new Battalion();
		b.setNumber(view.getValidNumber("podaj Id"));
		
		repo.createBattalion(b);
	}

	@Override
	public String getName() {
		return "CreateBattalion";
	}

}
