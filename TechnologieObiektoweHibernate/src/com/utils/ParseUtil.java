package com.utils;

import java.util.ArrayList;
import java.util.List;

import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.entities.Persistable;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.entities.Weapon;

public class ParseUtil {
	
	public static List<Weapon> parseWeaponList(List<Persistable> results) {
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		if(results.size()>0) {
			for(int i=0; i<results.size();i++) {
				Weapon w = (Weapon) results.get(i);
				weapons.add(w);
			}
		}
		return weapons;
	}
	
	public static List<Soldier> parseSoldierList(List<Persistable> results) {
		ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
		if(results.size()>0) {
			for(int i=0; i<results.size();i++) {
				Soldier s = (Soldier) results.get(i);
				soldiers.add(s);
			}
		}
		return soldiers;
	}
	
	public static List<Team> parseTeamList(List<Persistable> results) {
		ArrayList<Team> teams = new ArrayList<Team>();
		if(results.size()>0) {
			for(int i=0; i<results.size();i++) {
				Team t = (Team) results.get(i);
				teams.add(t);
			}
		}
		return teams;
	}
	
	public static List<Platoon> parsePlatoonList(List<Persistable> results) {
		List<Platoon> platoons = new ArrayList<Platoon>();
		if (results.size() > 0) {
			for (int i = 0; i < results.size(); i++) {
				Platoon p = (Platoon) results.get(i);
				platoons.add(p);
			}
		}
		return platoons;
	}
	
	public static List<Company> parseCompanyList(List<Persistable> results) {
		List<Company> companies = new ArrayList<Company>();
		if(results.size()>0) {
			for(int i=0; i<results.size(); i++) {
				Company c = (Company) results.get(i);
				companies.add(c);
			}
		}
		return companies;
	}
	
	public static List<Battalion> parseBattalionList(List<Persistable> results) {
		List<Battalion> battalions = new ArrayList<Battalion>();
		if(results.size()>0) {
			for(int i=0; i<results.size();i++) {
				Battalion b = (Battalion) results.get(i);
				battalions.add(b);
			}
		}
		return battalions;
	}
}
