package com.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.model.entities.Battalion;
import com.model.entities.Company;
import com.model.entities.Persistable;
import com.model.entities.Platoon;
import com.model.entities.Soldier;
import com.model.entities.Team;
import com.model.entities.Weapon;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParseUtil {
	
	public static List<Weapon> parseWeaponList(List<Persistable> results) {
		return results.stream().map(p -> (Weapon) p).collect(Collectors.toList());
	}
	
	public static List<Soldier> parseSoldierList(List<Persistable> results) {
		return results.stream().map(p -> (Soldier) p).collect(Collectors.toList());
	}
	
	public static List<Team> parseTeamList(List<Persistable> results) {
		return results.stream().map(p -> (Team) p).collect(Collectors.toList());
	}
	
	public static List<Platoon> parsePlatoonList(List<Persistable> results) {
		return results.stream().map(p -> (Platoon) p).collect(Collectors.toList());
	}
	
	public static List<Company> parseCompanyList(List<Persistable> results) {
		return results.stream().map(p -> (Company) p).collect(Collectors.toList());
	}
	
	public static List<Battalion> parseBattalionList(List<Persistable> results) {
		return results.stream().map(p -> (Battalion) p).collect(Collectors.toList());
	}
}
