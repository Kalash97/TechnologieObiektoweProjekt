package com.Actions;

import com.Entities.Company;
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

		String line;
		Company c;

		do {
			do {
				view.print("Podaj id kompanii do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			c = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));

		repo.deleteCompany(c);
	}

	@Override
	public String getName() {
		return "DeleteCompany";
	}

}
