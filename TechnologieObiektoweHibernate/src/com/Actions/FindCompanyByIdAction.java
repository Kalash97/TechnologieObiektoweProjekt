package com.Actions;

import java.util.List;

import com.Entities.Battalion;
import com.Entities.Company;
import com.Entities.Platoon;
import com.Exceptions.OperationCancelException;
import com.Repos.CompanyRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCompanyByIdAction implements Action{

	private View view;
	private CompanyRepo repo;
	
	@Override
	public void launch() {
		Company c;
		
		c = getValidCompany();
		
		view.print("Znaleziona kompania:");
		view.print("Numer: " + c.getNumber());
		view.print("Dow�dca:: Imi�:" + c.getCommander().getName()+ ", Nazwisko: "+c.getCommander().getLastName()+", Stopie�: "+c.getCommander().getRank());
		Battalion b = c.getBattalion();
		if(b!=null) {
			view.print("Batalion: Id:" + b.getId() + " Numer: " + b.getNumber());
		}else {
			view.print("Batalion: "+b);
		}
		view.print("Plutony w kompanii:");
		List<Platoon> list = c.getPlattons();
		if(list.size()>0) {
			for(int i=0; i<list.size(); i++) {
				view.print(i + ": " + "ID: "+list.get(i).getId() + ", Nr. plutonu: "+list.get(i).getNumber());
			}
		}
		view.print("");
	}

	private Company getValidCompany() {
		String line;
		Company c;
		do {
			do {
				view.print("Podaj id kompanii do znalezienia.(s�owo <<cancel>> zawraca)");
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
		return "FindCompanyById";
	}

}
