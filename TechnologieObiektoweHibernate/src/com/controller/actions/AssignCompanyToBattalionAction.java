package com.controller.actions;

import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCompanyToBattalionAction implements Action {

	private View view;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {
		Battalion b = getValidBattalion();
		Company c = getValidCompany();

		assignBattalionToCompany(b, c);	
	}

	public void assignBattalionToCompany(Battalion b, Company c) {
		if (!battalionContainsCompany(b, c)) {
			c.setBattalion(b);
			b.getCompanies().add(c);
			battalionRepo.updateBattalion(b);
			companyRepo.updateCompany(c);
		}
	}

	private boolean battalionContainsCompany(Battalion b, Company c) {
		for (int i = 0; i < b.getCompanies().size(); i++) {
			if (b.getCompanies().get(i).getNumber() == c.getNumber()) {
				view.print("Kompania o podanym numerze ju¿ istnieje w batalionie!");
				return true;
			}
		}
		return false;
	}

	private Battalion getValidBattalion() {
		String line;
		Battalion b;
		do {
			do {
				System.out.println("Podaj id batalionu do przypisania kompanii.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = battalionRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
	}

	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				System.out.println("Podaj id kompanii do przypisania batalionu.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = companyRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}

	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling AssignCompanyToBattalion");
		}
	}

	@Override
	public String getName() {
		return "AssignCompanyToBattalion";
	}

}
