package com.Actions;

import com.Entities.Battalion;
import com.Entities.Soldier;
import com.Exceptions.OperationCancelException;
import com.Repos.BattalionRepo;
import com.Repos.SoldierRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommanderToBattalionAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private BattalionRepo battalionRepo;
	
	@Override
	public void launch() {
		Battalion b = getValidBattalion();
		Soldier s = getValidSoldier();
		assignCommanderToCompany(b, s);
		battalionRepo.updateBattalion(b);
	}

	private void assignCommanderToCompany(Battalion b, Soldier s) {
		b.setCommander(s);
	}
	
	private Battalion getValidBattalion() {
		Battalion b;
		String line;
		do {
			do {
				view.print("Podaj id batalionu do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = battalionRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
	}
	
	private Soldier getValidSoldier() {
		Soldier s;
		String line;
		do {
			do {
				view.print("Podaj id nowego dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling assignCommander");
		}
	}
	
	@Override
	public String getName() {
		return "AssignCommanderToBattalion";
	}

}
