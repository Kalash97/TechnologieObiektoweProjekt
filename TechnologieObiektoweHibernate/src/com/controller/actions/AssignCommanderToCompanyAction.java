package com.controller.actions;

import java.util.List;

import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.repos.BattalionRepo;
import com.model.repos.CompanyRepo;
import com.model.repos.PlatoonRepo;
import com.model.repos.SoldierRepo;
import com.model.repos.TeamRepo;
import com.utils.ValidUtil;
import com.utils.enums.Rank;
import com.utils.exceptions.OperationCancelException;
import com.view.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommanderToCompanyAction implements Action {

	private View view;
	private SoldierRepo soldierRepo;
	private BattalionRepo battalionRepo;
	private CompanyRepo companyRepo;
	private PlatoonRepo platoonRepo;
	private TeamRepo teamRepo;

	@Override
	public void launch() {
		Company c = getValidCompany();
		Soldier s = getValidSoldier();

		detachCommanderFromTeams(s);
		detachSoldierFromTeams(s);
		detachCommanderFromPlatoons(s);
		detachCommanderFromCompanies(s);
		detachCommanderFromBattalions(s);

		assignCommanderToCompany(c, s);
	}

	private void assignCommanderToCompany(Company c, Soldier s) {
		if (ValidUtil.isRankProper(s, Rank.CAPITAN)) {
			c.setCommander(s);
			companyRepo.updateCompany(c);
		} else {
			view.print("¯o³nierz ma nieodpowiedni stopieñ");
			return;
		}
	}

	private Company getValidCompany() {
		Company c;
		String line;
		do {
			do {
				view.print("Podaj id kompanii do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			c = companyRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(c));
		return c;
	}

	private Soldier getValidSoldier() {
		Soldier s;
		String line;
		do {
			do {
				view.print("Podaj id nowego dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}

	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling assignCommander");
		}
	}

	private void detachCommanderFromBattalions(Soldier s) {
		List<Battalion> battalions = soldierRepo.findBattalionOfCommander(s);
		if (battalions.size() > 0) {
			for (Battalion battalion : battalions) {
				battalion.setCommander(null);
				battalionRepo.updateBattalion(battalion);
			}
		}
	}

	private void detachCommanderFromCompanies(Soldier s) {
		List<Company> companies = soldierRepo.findCompanyOfCommander(s);
		if (companies.size() > 0) {
			for (Company company : companies) {
				company.setCommander(null);
				companyRepo.updateCompany(company);
			}
		}
	}

	private void detachCommanderFromPlatoons(Soldier s) {
		List<Platoon> platoons = soldierRepo.findPlatoonOfCommander(s);
		if (platoons.size() > 0) {
			for (Platoon platoon : platoons) {
				platoon.setCommander(null);
				platoonRepo.updatePlatoon(platoon);
			}
		}
	}

	private void detachSoldierFromTeams(Soldier s) {
		List<Team> teams = soldierRepo.findTeamsOfSoldier(s);
		if (teams.size() > 0) {
			for (Team team : teams) {
				team.getSoldiers().remove(s);
				s.setTeam(null);
				teamRepo.updateTeam(team);
				soldierRepo.updateSoldier(s);
			}
		}
	}

	private void detachCommanderFromTeams(Soldier s) {
		List<Team> teams = soldierRepo.findTeamsOfCommander(s);
		if (teams.size() > 0) {
			for (Team team : teams) {
				team.setCommander(null);
				teamRepo.updateTeam(team);
			}
		}
	}

	@Override
	public String getName() {
		return "AssignCommanderToCompany";
	}

}
