package com.Actions.CompanyActions;

import com.Actions.Action;
import com.Entities.Company;
import com.Exceptions.OperationCancelException;
import com.Repos.CompanyRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCompanyAction implements Action{

	private View view;
	private CompanyRepo repo;
	
	@Override
	public void launch() {
		Company c = getValidCompany();
		view.print("Podaj nowy numer.(Zostaw puste jeœli nie chcesz zmieniaæ)");
		String line = getCompanyNumber();
		if(!line.equals("")) {
			c.setNumber(Long.parseLong(line));
		}
		
		repo.updateCompany(c);
	}

	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				view.print("Podaj id kompanii do modyfikacji.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = repo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}

	private String getCompanyNumber() {
		String companyNumber;
		do {
			view.print("Podaj numer kompanii");
			companyNumber=view.read();
			if(companyNumber.equals("")) {
				break;
			}
		}while(!ValidUtil.isLongInstance(companyNumber));
		return companyNumber;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling updateCompany");
		}
	}
	
	@Override
	public String getName() {
		return "UpdateCompany";
	}

}
