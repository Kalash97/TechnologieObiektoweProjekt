package com.Actions.BattalionActions;

import java.util.List;

import com.Actions.Action;
import com.Entities.Battalion;
import com.Repos.BattalionRepo;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionsWithoutCompaniesAction implements Action{

	private View view;
	private BattalionRepo battalionRepo;
	
	@Override
	public void launch() {
		List<Battalion> battalions = battalionRepo.findBattalionsWithoutCompanies();
		showBattalions(battalions);
	}

	private void showBattalions(List<Battalion> battalions) {
		if(battalions.size()>0) {
			for(int i=0; i<battalions.size();i++) {
				Battalion b = battalions.get(i);
				view.print(i + ": ID: "+b.getId()+", Numer: "+b.getNumber());
			}
		}else {
			view.print("Brak batalionów bez kompanii");
		}
	}

	@Override
	public String getName() {
		return "FindBattalionsWithoutCompanies";
	}

}
