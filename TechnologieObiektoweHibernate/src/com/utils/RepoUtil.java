package com.utils;

import java.util.List;

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
import com.utils.enums.BloodType;
import com.utils.enums.Rank;
import com.utils.enums.WeaponType;
import com.view.View;

public class RepoUtil {
	
	public static WeaponType getValidWeaponType(View view) {
		while(true) {
			try {
				view.print("Podaj typ broni");
				return WeaponType.valueOf(view.read());
			} catch (Exception e) {
				
			}
		}
	}
	
	public static Rank getValidRank(View view) {
		while(true) {
			try {
				view.print("Podaj stopieñ");
				return Rank.valueOf(view.read());
			} catch (Exception e) {
				
			}
		}
	}
	
	public static BloodType getValidBloodType(View view) {
		while(true) {
			try {
				view.print("Podaj grupê krwi");
				return BloodType.valueOf(view.read());
			} catch (Exception e) {
				
			}
		}
	}
	
	 public static Battalion getValidBattalion(View view, BattalionRepo battalionRepo) {
		Battalion b = null;
		while(b==null) {
			long id = view.getValidNumberCancellable("ID");
			b = battalionRepo.findById(id);
		}
		return b;
	}
	
	public static Company getValidCompany(View view, CompanyRepo companyRepo) {
		Company c = null;
		while (c == null) {
			long id = view.getValidNumberCancellable("Podaj id kompanii do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
			c = companyRepo.findById(id);
		}
		return c;
	}
	
	 public static Platoon getValidPlatoon(View view, PlatoonRepo platoonRepo) {
			Platoon p = null;
			while(p==null) {
				long id = view.getValidNumberCancellable("ID");
				p = platoonRepo.findById(id);
			}
			return p;
		}
	 
	 public static Team getValidTeam(View view, TeamRepo teamRepo) {
			Team t = null;
			while (t == null) {
				long id = view.getValidNumberCancellable("Podaj id kompanii do przypisania dowódcy.(s³owo <<cancel>> zawraca)");
				t = teamRepo.findById(id);
			}
			return t;
		}
	
	public static Soldier getValidSoldier(View view, SoldierRepo soldierRepo) {
		Soldier s = null;
		while(s==null) {
			long id = view.getValidNumberCancellable("Podaj id ¿o³nierza.(s³owo <<cancel>> zawraca)");
			s = soldierRepo.findById(id);
		}
		return s;
	}
	
	 public static Weapon getValidWeapon(View view, WeaponRepo weaponRepo) {
			Weapon w = null;
			while(w==null) {
				long id = view.getValidNumberCancellable("ID broni");
				w = weaponRepo.findById(id);
			}
			return w;
		}
	
	public static Soldier getValidSoldierWithMinimumRank(View view, SoldierRepo soldierRepo, Rank rank) {
		Soldier s = null;
		while(s==null || !ValidUtil.isRankProper(s, rank)) {
			long id = view.getValidNumberCancellable("Podaj id dowódcy.(s³owo <<cancel>> zawraca)");
			s = soldierRepo.findById(id);
		}
		return s;
	}
	
	public static Soldier getValidSoldierWithRankInRange(View view, SoldierRepo soldierRepo, Rank rank1, Rank rank2) {
		Soldier s = null;
		while(s==null || !ValidUtil.isRankProper(s, rank1, rank2)) {
			long id = view.getValidNumberCancellable("Podaj id dowódcy.(s³owo <<cancel>> zawraca)");
			s = soldierRepo.findById(id);
		}
		return s;
	}
	
	public static Soldier getValidSoldierWithLowerOrEqualRank(View view, SoldierRepo soldierRepo, Rank rank) {
		Soldier s = null;
		while(s==null || !ValidUtil.isRankLowerOrEqual(s, rank)) {
			long id = view.getValidNumberCancellable("Podaj id dowódcy.(s³owo <<cancel>> zawraca)");
			s = soldierRepo.findById(id);
		}
		return s;
	}
	
	public static void detachCommanderFromBattalions(Soldier s, BattalionRepo battalionRepo) {
		List<Battalion> battalions = battalionRepo.findBattalionOfCommander(s);
		if (battalions.size() > 0) {
			for (Battalion battalion : battalions) {
				battalion.setCommander(null);
				battalionRepo.update(battalion);
			}
		}
	}

	public static void detachCommanderFromCompanies(Soldier s, CompanyRepo companyRepo) {
		List<Company> companies = companyRepo.findCompanyOfCommander(s);
		if (companies.size() > 0) {
			for (Company company : companies) {
				company.setCommander(null);
				companyRepo.update(company);
			}
		}
	}

	public static void detachCommanderFromPlatoons(Soldier s, PlatoonRepo platoonRepo) {
		List<Platoon> platoons = platoonRepo.findPlatoonOfCommander(s);
		if (platoons.size() > 0) {
			for (Platoon platoon : platoons) {
				platoon.setCommander(null);
				platoonRepo.update(platoon);
			}
		}
	}

	public static void detachSoldierFromTeams(Soldier s, TeamRepo teamRepo, SoldierRepo soldierRepo) {
		List<Team> teams = teamRepo.findTeamsOfSoldier(s);
		if (teams.size() > 0) {
			for (Team team : teams) {
				team.getSoldiers().remove(s);
				s.setTeam(null);
				teamRepo.update(team);
				soldierRepo.update(s);
			}
		}
	}

	public static void detachCommanderFromTeams(Soldier s, TeamRepo teamRepo) {
		List<Team> teams = teamRepo.findTeamsOfCommander(s);
		if (teams.size() > 0) {
			for (Team team : teams) {
				team.setCommander(null);
				teamRepo.update(team);
			}
		}
	}
	
	public static void detachWeaponsFromSoldier(Soldier s, WeaponRepo weaponRepo, SoldierRepo soldierRepo) {
		Weapon w;
		if (s.getWeapons().size() > 0) {
			for (int i = 0; i < s.getWeapons().size(); i++) {
				w = s.getWeapons().get(i);
				w.setSoldier(null);
				weaponRepo.update(w);
			}
			s.getWeapons().clear();
			soldierRepo.update(s);
		}
	}
	
	public static boolean battalionContainsCompany(Battalion b, Company c) {
		for (int i = 0; i < b.getCompanies().size(); i++) {
			if (b.getCompanies().get(i).getNumber() == c.getNumber()) {				
				return true;
			}
		}
		return false;
	}
	
	public static boolean companyContainsPlatoon(Company c, Platoon p) {
		for (int i = 0; i < c.getPlattons().size(); i++) {
			if (c.getPlattons().get(i).getNumber() == p.getNumber()) {				
				return true;
			}
		}
		return false;
	}
	
	public static boolean platoonContainsTeam(Platoon p, Team t) {
		for (int i = 0; i < p.getTeams().size(); i++) {
			if (p.getTeams().get(i).getNumber() == t.getNumber()) {
				return true;
			}
		}
		return false;
	}
	
	public static void removeCompaniesFromBattalion(Battalion b, CompanyRepo companyRepo, BattalionRepo battalionRepo) {
		if(b.getCompanies().size()>0) {
			for(int i=0; i<b.getCompanies().size();i++) {
				Company c=b.getCompanies().get(i);
				c.setBattalion(null);
				companyRepo.update(c);
			}
			battalionRepo.update(b);
		}
	}
	
	public static void removeCommanderFromBattalion(Battalion b, BattalionRepo battalionRepo) {
		if(b.getCommander()!=null) {
			b.setCommander(null);
			battalionRepo.update(b);
		}
	}
	
	public static void removeComanderFromCompany(Company c, CompanyRepo companyRepo) {
		if(c.getCommander()!=null) {
			c.setCommander(null);
			companyRepo.update(c);
		}
	}
	
	public static void removePlatoonsFromCompany(Company c, PlatoonRepo platoonRepo, CompanyRepo companyRepo) {
		Platoon p;
		if(c.getPlattons().size()>0) {
			for(int i=0; i<c.getPlattons().size();i++) {
				p=c.getPlattons().get(i);
				p.setCompany(null);
				platoonRepo.update(p);
			}
			companyRepo.update(c);
		}
	}
	
	public static void removeTeamsFromPlatoon(Platoon p, TeamRepo teamRepo, PlatoonRepo platoonRepo) {
		Team t;
		if(p.getTeams().size()>0) {
			for(int i=0; i<p.getTeams().size();i++) {
				t=p.getTeams().get(i);
				t.setPlatoon(null);
				teamRepo.update(t);
			}
			platoonRepo.update(p);
		}
	}
	
	public static void removeCommanderFromPlatoon(Platoon p, PlatoonRepo platoonRepo) {
		if(p.getCommander()!=null) {
			p.setCommander(null);
			platoonRepo.update(p);
		}
	}
	
	public static void removeSoldiersFromTeam(Team t, SoldierRepo soldierRepo, TeamRepo teamRepo) {
		Soldier s;
		if(t.getSoldiers().size()>0) {
			for(int i=0; i<t.getSoldiers().size();i++) {
				s=t.getSoldiers().get(i);
				s.setTeam(null);
				soldierRepo.update(s);
			}
			teamRepo.update(t);
		}
	}

	public static void removeCommanderFromTeam(Team t, SoldierRepo soldierRepo) {
		Soldier s;
		if(t.getCommander()!=null) {
			s = t.getCommander();
			t.setCommander(null);
			soldierRepo.update(s);
		}
	}
}
