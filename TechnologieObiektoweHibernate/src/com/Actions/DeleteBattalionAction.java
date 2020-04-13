package com.Actions;

import com.Entities.Battalion;
import com.Repos.BattalionRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteBattalionAction implements Action{
	
	private View view;
	private BattalionRepo repo;
	
	@Override
	public void launch() {
		
		view.print("Podaj id batalionu do usuniêcia.(s³owo <<cancel>> zawraca)");
		
		
		String line = view.read();
		if (line.equals("cancel")) {
			return;
		}
		
		Battalion b =repo.findById(Long.parseLong(line));
		
		repo.deleteBattalion(b);
	}

	@Override
	public String getName() {
		return "DeleteBattalion";
	}

}
