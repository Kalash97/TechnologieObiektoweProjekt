package com.Actions.BattalionActions;

import java.util.Arrays;

import com.Actions.Action;
import com.Entities.Battalion;
import com.Repos.BattalionRepo;
import com.Utils.ViewHelper;
import com.View.View;

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

		ViewHelper.printResults(ViewHelper.compainiesToPersistable(b.getCompanies()), view);
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
