package com.Utils;

import com.Entities.Persistable;
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
	
	public static boolean isValid(String line) {
		
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
	
}
