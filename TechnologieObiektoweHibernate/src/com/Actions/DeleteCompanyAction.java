package com.Actions;

import com.Entities.Company;
import com.Exceptions.OperationCancelException;
import com.Repos.CompanyRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCompanyAction implements Action {

	private View view;
	CompanyRepo repo;

	@Override
	public void launch() {

		//TO DO detaching entities
		Company c;

		c = getValidCompany();

		repo.deleteCompany(c);
	}

	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				view.print("Podaj id kompanii do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling deleteCompany");
		}
	}
	
	@Override
	public String getName() {
		return "DeleteCompany";
	}

}
