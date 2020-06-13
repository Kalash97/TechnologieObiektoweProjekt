package com.controller.actions;

public class ExitAction extends Action{

	@Override
	public void launch() {
		System.exit(1);
	}

	@Override
	public String getName() {
		return "Exit";
	}

}
