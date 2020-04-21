package com.Actions;

import com.Entities.Company;
import com.Entities.Soldier;
import com.Exceptions.OperationCancelException;
import com.Repos.CompanyRepo;
import com.Repos.SoldierRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommanderToCompanyAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private CompanyRepo companyRepo;
	
	@Override
	public void launch() {
		Company c = getValidCompany();
		Soldier s = getValidSoldier();
		assignCommanderToCompany(c, s);
		companyRepo.updateCompany(c);
	}
	
	private void assignCommanderToCompany(Company c, Soldier s) {
		c.setCommander(s);
	}
	
	private Company getValidCompany() {
		Company c;
		String line;
		do {
			do {
				view.print("Podaj id kompanii do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = companyRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
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
		return "AssignCommanderToCompany";
	}

}
