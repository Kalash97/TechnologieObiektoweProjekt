package com.Actions;

import com.Entities.Battalion;
import com.Repos.BattalionRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBattalionAction implements Action{
	
	private View view;
	private BattalionRepo repo;

	@Override
	public void launch() {
		Battalion b = new Battalion();
		
		String battalionNumber;
		do {
			view.print("Podaj numer batalionu");
			battalionNumber=view.read();
		}while(!ValidUtil.isValid(battalionNumber));
		
		b.setNumber(Long.parseLong(battalionNumber));
		
		repo.createBattalion(b);
	}

	@Override
	public String getName() {
		return "CreateBattalion";
	}

}
