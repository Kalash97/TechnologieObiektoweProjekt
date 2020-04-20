package com.Actions;

import com.Entities.Battalion;
import com.Exceptions.OperationCancelException;
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
		//TO DO detaching entities
		Battalion b;

		b = getValidBattalion();

		repo.deleteBattalion(b);
	}

	private Battalion getValidBattalion() {
		Battalion b;
		String line;
		do {
			do {
				view.print("Podaj id batalionu do usuniêcia.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
	}

	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling deleteBattalion");
		}
	}
	
	@Override
	public String getName() {
		return "DeleteBattalion";
	}

}
