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
