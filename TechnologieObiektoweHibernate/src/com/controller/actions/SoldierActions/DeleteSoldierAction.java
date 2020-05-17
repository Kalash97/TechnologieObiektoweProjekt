package com.controller.actions.SoldierActions;

import java.util.List;

import com.controller.actions.Action;
import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.entities.Weapon;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.model.repos.WeaponRepo;
import com.utils.ValidUtil;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSoldierAction implements Action {

	private View view;
	private SoldierRepo soldierRepo;
	private WeaponRepo weaponRepo;
	private TeamRepo teamRepo;
	private PlatoonRepo platoonRepo;
	private CompanyRepo companyRepo;
	private BattalionRepo battalionRepo;

	@Override
	public void launch() {

		Soldier s = getValidSoldier();
		removeWeaponsFromSoldier(s);
		removeSoldierFromTeam(s);
		removeCommanderFromPlatoon(s);
		removeCommanderFromCompany(s);
		removeCommanderFromBattalion(s);
		
		soldierRepo.deleteSoldier(s);
	}

	private void removeCommanderFromBattalion(Soldier s) {
		List<Battalion> battalions = soldierRepo.findBattalionOfCommander(s);
		if(battalions.size()>0) {
			for(int i=0; i<battalions.size(); i++) {
				Battalion b = battalions.get(i);
				b.setCommander(null);
				battalionRepo.updateBattalion(b);
			}
		}
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
