package com.Actions;

import java.util.List;

import com.Entities.Company;
import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Entities.Team;
import com.Entities.Weapon;
import com.Exceptions.OperationCancelException;
import com.Repos.CompanyRepo;
import com.Repos.PlatoonRepo;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Repos.WeaponRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSoldierAction implements Action {

	private View view;
	private SoldierRepo soldierRepo;
	private WeaponRepo weaponRepo;
	private TeamRepo teamRepo;
	private PlatoonRepo platoonRepo;
	private CompanyRepo companyRepo;

	@Override
	public void launch() {

		Soldier s = getValidSoldier();
		removeWeaponsFromSoldier(s);
		removeSoldierFromTeam(s);
		removeCommanderFromPlatoon(s);
		removeCommanderFromCompany(s);
		
		soldierRepo.deleteSoldier(s);
	}

	private void removeCommanderFromCompany(Soldier s) {
		List<Company> companies = soldierRepo.findCompanyOfCommander(s);
		if(companies.size()>0) {
			for(int i=0; i<companies.size();i++) {
				Company c = companies.get(i);
				c.setCommander(null);
				companyRepo.updateCompany(c);
			}
		}
	}
	
	private void removeCommanderFromPlatoon(Soldier s) {
		List<Platoon> platoons = soldierRepo.findPlatoonOfCommander(s);
		if(platoons.size()>0) {
			for(int i=0; i<platoons.size(); i++) {
				Platoon p = platoons.get(i);
				p.setCommander(null);
				platoonRepo.updatePlatoon(p);
			}
		}
	}
	
	private void removeSoldierFromTeam(Soldier s) {
		Team t;
		if (s.getTeam() != null) {
			t = s.getTeam();
			if (t.getCommander().getId() == s.getId()) {
				t.setCommander(null);
				t.getSoldiers().remove(s);
				teamRepo.updateTeam(t);
			}
			soldierRepo.updateSoldier(s);
		}
	}

	private void removeWeaponsFromSoldier(Soldier s) {
		Weapon w;
		if (s.getWeapons().size() > 0) {
			for (int i = 0; i < s.getWeapons().size(); i++) {
				w = s.getWeapons().get(i);
				w.setSoldier(null);
				weaponRepo.updateWeapon(w);
			}
			s.getWeapons().clear();
			soldierRepo.updateSoldier(s);
		}
	}

	private Soldier getValidSoldier() {
		String line;
		Soldier s;
		do {
			do {
				view.print("Podaj id ¿o³nierza do usuniêcia.");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}
	
	private void canceling(String line) {
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling deleteSoldier");
		}
	}
	
	@Override
	public String getName() {
		return "DeleteSoldier";
	}

}
