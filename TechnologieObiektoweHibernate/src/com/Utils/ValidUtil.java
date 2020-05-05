package com.Utils;

import com.Entities.Persistable;
import com.Entities.Soldier;
import com.Enums.BloodType;
import com.Enums.Rank;
import com.Enums.WeaponType;

public class ValidUtil {

	public static boolean isValid(String value, BloodType[] allowed) {
		
		try {
			BloodType.valueOf(value.toUpperCase());
		}catch (IllegalArgumentException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isValid(String value, Rank[] allowed) {
		
		try {
			Rank.valueOf(value.toUpperCase());
		}catch (IllegalArgumentException e) {
			return false;
		}
		
		return true;
		
	}
	
	public static boolean isValid(String value, WeaponType[] allowed) {
		
		try {
			WeaponType.valueOf(value.toUpperCase());
		}catch(IllegalArgumentException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isLongInstance(String line) {
		
		try {
			Long.parseLong(line);
		}catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isValid(Persistable persistable) {
		if(persistable==null) {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean isRankProper(Soldier soldier, Rank rank1, Rank rank2) {
		if((soldier.getRank().compareTo(rank1)>0 || soldier.getRank().compareTo(rank1)==0) && (soldier.getRank().compareTo(rank2)<0 || soldier.getRank().compareTo(rank2)==0)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean isRankProper(Soldier soldier, Rank rank) {
		if(soldier.getRank().compareTo(rank)==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isRankLowerOrEqual(Soldier soldier, Rank rank) {
		if(soldier.getRank().compareTo(rank)<0 || soldier.getRank().compareTo(rank)==0) {
			return true;
		}else {
			return false;
		}
	}
}
