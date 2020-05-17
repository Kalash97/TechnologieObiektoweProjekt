package com.utils;

import com.model.entities.Soldier;
import com.utils.enums.Rank;

public class ValidUtil {
	
	public static boolean isRankProper(Soldier soldier, Rank rank1, Rank rank2) {
		return soldier.getRank().compareTo(rank1) >=0 && soldier.getRank().compareTo(rank2) <=0;
	}
	
	public static boolean isRankProper(Soldier soldier, Rank rank) {
		return soldier.getRank().compareTo(rank)==0;
	}
	
	public static boolean isRankLowerOrEqual(Soldier soldier, Rank rank) {
		return soldier.getRank().compareTo(rank)<=0;
		
	}
}
