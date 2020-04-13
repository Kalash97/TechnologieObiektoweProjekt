package com.Actions;

import com.Entities.Battalion;
import com.Repos.BattalionRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteBattalionAction implements Action {

	private View view;
	private BattalionRepo repo;

	@Override
	public void launch() {

		Battalion b;
		String line;

		do {
			do {
				view.print("Podaj id batalionu do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				if (line.equals("cancel")) {
					return;
				}
			} while (!ValidUtil.isValid(line));
			b = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));

		repo.deleteBattalion(b);
	}

	@Override
	public String getName() {
		return "DeleteBattalion";
	}

}
