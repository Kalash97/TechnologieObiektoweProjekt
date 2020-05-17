package com.controller.actions.CompanyActions;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveCommanderFromCompanyAction implements Action{

	private View view;
	private CompanyRepo repo;
	
	@Override
	public void launch() {
		Company c = getValidCompany();
		
		c.setCommander(null);
		repo.updateCompany(c);
	}

	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				view.print("Podaj id kompanii do usuniêcia dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling findSoldier");
		}
	}
	
	@Override
	public String getName() {
		return "RemoveCommanderFromCompany";
	}

}
