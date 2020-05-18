package com.controller.actions.battalionActions;

import java.util.Arrays;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.repos.BattalionRepo;
import com.utils.ViewHelper;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBattalionByIdAction implements Action {

	private View view;
	private BattalionRepo repo;

	@Override
	public void launch() {

		Battalion b;

		b = getValidBattalion();
		ViewHelper.printResults(Arrays.asList(b), view);
		view.print("");

		ViewHelper.printResults(b.getCompanies(), view);
		view.print("");
	}

	private Battalion getValidBattalion() {

		while (true) {
			long id = view.getValidNumberCancellable("Podaj ID batalionu");
			Battalion b = repo.findById(id);
			if (b != null) {
				return b;
			}
		}
	}

	@Override
	public String getName() {
		return "FindBattalionById";
	}

}
