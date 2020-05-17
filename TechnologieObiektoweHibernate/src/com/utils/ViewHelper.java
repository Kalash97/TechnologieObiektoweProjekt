package com.utils;

import java.util.List;
import java.util.Set;

import com.view.View;

public class ViewHelper {

	@SuppressWarnings("rawtypes")
	public static void printResults(List results, View view) {
		for(Object o : results) {
			view.print(o.toString());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void printResults(Set results, View view) {
		for(Object o : results) {
			view.print(o.toString());
		}
	}
}
