package com.Actions.BattalionActions;

import com.Actions.Action;
import com.Entities.Battalion;
import com.Exceptions.OperationCancelException;
import com.Repos.BattalionRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromBattalionAction implements Action{

	private View view;
	private BattalionRepo repo;
	
	@Override
	public void launch() {
		Battalion b = getValidBattalion();
		
		b.setCommander(null);
		repo.updateBattalion(b);
	}

	private Battalion getValidBattalion() {
		String line;
		Battalion b;
		do {
			do {
				view.print("Podaj id batalionu do usuniêcia dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling findSoldier");
		}
	}
	
	@Override
	public String getName() {
		return "RemoveCommanderFromBattalion";
	}

}
