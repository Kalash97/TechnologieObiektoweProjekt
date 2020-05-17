package com.controller.actions.CompanyActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Company;
import com.model.repos.CompanyRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompanyByIdAction implements Action {

	private View view;
	private CompanyRepo repo;

	@Override
	public void launch() {
		Company c;

		c = getValidCompany();

		ViewHelper.printResults(Arrays.asList(c), view);

		try {
			view.print("-----Batalion:");
			ViewHelper.printResults(Arrays.asList(c.getBattalion()), view);
		} catch (NullPointerException e) {
			view.print("Brak batalionu");
		}
		view.print("");

		view.print("-----Plutony:");
		ViewHelper.printResults(c.getPlattons(), view);
		view.print("");
	}

	private Company getValidCompany() {
	
		while (true) {
			long id = view.getValidNumberCancellable("Podaj ID kompanii");
			Company c = repo.findById(id);
			if (c != null) {
				return c;
			}
		}
	}

	@Override
	public String getName() {
		return "FindCompanyById";
	}

}
