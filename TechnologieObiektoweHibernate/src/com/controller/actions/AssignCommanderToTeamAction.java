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
public class AssignCommanderToTeamAction implements Action {

	private View view;
	private TeamRepo teamRepo;
	private PlatoonRepo platoonRepo;
	private CompanyRepo companyRepo;
	private BattalionRepo battalionRepo;
	private SoldierRepo soldierRepo;

	@Override
	public void launch() {
		Team t;
		Soldier s;

		t = getValidTeam();

		for (int i = 0; i < t.getSoldiers().size(); i++) {
			System.out.println(t.getSoldiers().get(i));
		}

		s = getValidSoldier();

		detachCommanderFromTeams(s);
		detachCommanderFromPlatoons(s);
		detachCommanderFromCompanies(s);
		detachCommanderFromBattalions(s);

		assignCommanderToTeam(t, s);
		
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

	private void detachCommanderFromTeams(Soldier s) {
		List<Team> teams = soldierRepo.findTeamsOfCommander(s);
		if (teams.size() > 0) {
			for (Team team : teams) {
				team.setCommander(null);
				teamRepo.updateTeam(team);
			}
		}
	}

	private void assignCommanderToTeam(Team t, Soldier s) {
		if (ValidUtil.isRankProper(s, Rank.CORPORAL, Rank.MASTER_SERGEANT)) {
			if (t.getSoldiers().contains(s)) {
				t.setCommander(s);
				teamRepo.updateTeam(t);
				soldierRepo.updateSoldier(s);
			} else {
				s.setTeam(t);
				t.getSoldiers().add(s);
				t.setCommander(s);
				teamRepo.updateTeam(t);
				soldierRepo.updateSoldier(s);
			}
		}else {
			view.print("�o�nierz ma nieodpowiedni stopie�");
			return;
		}
	}

	private Soldier getValidSoldier() {
		Soldier s;
		String line;
		do {
			do {
				view.print("Podaj id nowego dow�dcy.(s�owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			s = soldierRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(s));
		return s;
	}

	private Team getValidTeam() {
		Team t;
		String line;
		do {
			do {
				view.print("Podaj id dru�yny do przypisania dow�dcy.(s�owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			t = teamRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(t));
		return t;
	}

	private void canceling(String line) {
		if ("cancel".equals(line)) {
			throw new OperationCancelException("canceling assignCommander");
		}
	}

	@Override
	public String getName() {
		return "AssignCommanderToTeam";
	}

}
