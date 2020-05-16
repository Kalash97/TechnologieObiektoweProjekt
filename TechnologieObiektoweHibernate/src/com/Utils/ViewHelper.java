package com.Utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.Entities.Battalion;
import com.Entities.Company;
import com.Entities.Persistable;
import com.Entities.Platoon;
import com.Entities.Soldier;
import com.Entities.Team;
import com.Entities.Weapon;
import com.View.View;

public class ViewHelper {
	
	public static void printResults(List<Persistable> results, View view) {
		for(Persistable p : results) {
			view.print(p.toString());
		}
	}

	public static List<Persistable> weaponsToPersistable(List<Weapon> weapons) {
		return weapons.stream().map(w->(Persistable)w).collect(Collectors.toList());
	}

	public static List<Persistable> soldiersToPersistable(List<Soldier> soldiers) {
		return soldiers.stream().map(s->(Persistable)s).collect(Collectors.toList());
	}

	public static List<Persistable> teamsToPersistable(List<Team> teams){
		return teams.stream().map(t->(Persistable)t).collect(Collectors.toList());
	}
	
	
	public static List<Persistable> platoonsToPersistable(List<Platoon> platoons){
		return platoons.stream().map(p->(Persistable)p).collect(Collectors.toList());
	}
	
	public static List<Persistable> compainiesToPersistable(List<Company> companies){
		return companies.stream().map(c->(Persistable)c).collect(Collectors.toList());
	}
	
	public static List<Persistable> battalionsToPersistable(List<Battalion> battalions){
		return battalions.stream().map(b->(Persistable)b).collect(Collectors.toList());
	}
	
	public static List<Persistable> teamsToPersistable(Set<Team> teams){
		return teams.stream().map(t->(Persistable)t).collect(Collectors.toList());
	}
	
	public static List<Persistable> platoonsToPersistable(Set<Platoon> platoons){
		return platoons.stream().map(p->(Persistable)p).collect(Collectors.toList());
	}
	
	public static List<Persistable> compainiesToPersistable(Set<Company> companies){
		return companies.stream().map(c->(Persistable)c).collect(Collectors.toList());
	}
	
	public static List<Persistable> battalionsToPersistable(Set<Battalion> battalions){
		return battalions.stream().map(b->(Persistable)b).collect(Collectors.toList());
	}
}
