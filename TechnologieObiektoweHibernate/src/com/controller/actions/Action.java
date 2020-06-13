package com.controller.actions;

import lombok.Getter;

public abstract class Action {
	
	private static int actionsCounter = 1;
	
	@Getter
	private int id = actionsCounter++;
	
	public abstract void launch();
	public abstract String getName();
		
}
