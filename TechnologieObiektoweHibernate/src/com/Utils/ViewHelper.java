package com.Utils;

import java.util.List;
import java.util.stream.Collectors;

import com.Entities.Persistable;
import com.Entities.Soldier;
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

}
