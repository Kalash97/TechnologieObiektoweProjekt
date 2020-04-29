package com.Actions;

import java.util.List;

import com.Entities.Battalion;
import com.Entities.Company;
import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Entities.Team;
import com.Exceptions.OperationCancelException;
import com.Repos.BattalionRepo;
import com.Repos.CompanyRepo;
import com.Repos.PlatoonRepo;
import com.Repos.SoldierRepo;
import com.Repos.TeamRepo;
import com.Utils.ValidUtil;
import com.View.View;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommanderToBattalionAction implements Action{

	private View view;
	private SoldierRepo soldierRepo;
	private BattalionRepo battalionRepo;
	private TeamRepo teamRepo;
	private PlatoonRepo platoonRepo;
	private CompanyRepo companyRepo;
	
	@Override
	public void launch() {
		Battalion b = getValidBattalion();
		Soldier s = getValidSoldier();
		
		detachCommanderFromTeams(s);
		detachSoldierFromTeams(s);
		detachCommanderFromPlatoons(s);
		detachCommanderFromCompanies(s);
		detachCommanderFromBattalions(s);
		
		assignCommanderToCompany(b, s);
		battalionRepo.updateBattalion(b);
	}

	private void assignCommanderToCompany(Battalion b, Soldier s) {
		b.setCommander(s);
	}
	
	private Battalion getValidBattalion() {
		Battalion b;
		String line;
		do {
			do {
				view.print("Podaj id batalionu do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				line = view.read();
				canceling(line);
			} while (!ValidUtil.isLongInstance(line));
			b = battalionRepo.findById(Long.parseLong(line));
		} while (!ValidUtil.isValid(b));
		return b;
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
		if("cancel".equals(line)) {
			throw new OperationCancelException("canceling assignCommander");
		}
	}
	
	private void detachCommanderFromBattalions(Soldier s) {
		List<Battalion> battalions = soldierRepo.findBattalionOfCommander(s);
		if(battalions.size()>0) {
			for(Battalion battalion : battalions) {
				battalion.setCommander(null);
				battalionRepo.updateBattalion(battalion);
			}
		}
	}

	private void detachCommanderFromCompanies(Soldier s) {
		List<Company> companies = soldierRepo.findCompanyOfCommander(s);
		if(companies.size()>0) {
			for(Company company : companies) {
				company.setCommander(null);
				companyRepo.updateCompany(company);
			}
		}
	}

	private void detachCommanderFromPlatoons(Soldier s) {
		List<Platoon> platoons = soldierRepo.findPlatoonOfCommander(s);
		if(platoons.size()>0) {
			for(Platoon platoon : platoons) {
				platoon.setCommander(null);
				platoonRepo.updatePlatoon(platoon);
			}
		}
	}
	
	private void detachSoldierFromTeams(Soldier s) {
		List<Team> teams = soldierRepo.findTeamsOfSoldier(s);
		if(teams.size()>0) {
			for(Team team : teams) {
				team.getSoldiers().remove(s);
				s.setTeam(null);
				teamRepo.updateTeam(team);
				soldierRepo.updateSoldier(s);
			}
		}
	}
	
	private void detachCommanderFromTeams(Soldier s) {
		List<Team> teams = soldierRepo.findTeamsOfCommander(s);
		if(teams.size()>0) {
			for(Team team : teams) {
				team.setCommander(null);
				teamRepo.updateTeam(team);
			}
		}
	}
	
	@Override
	public String getName() {
		return "AssignCommanderToBattalion";
	}

}
