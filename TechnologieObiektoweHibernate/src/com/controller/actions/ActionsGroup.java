package com.controller.actions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ActionsGroup {

	public ActionsGroup(String groupName) {
		super();
		this.groupName = groupName;
	}

	@Getter
	private String groupName;

	@Getter
	private List<Action> actions = new ArrayList<Action>();

	public void addAction(Action a) {
		actions.add(a);
	}

	public static ActionsGroup createGroup(String groupName, Action... actions) {
		ActionsGroup group = new ActionsGroup(groupName);
		for (Action a : actions) {
			group.addAction(a);
		}
		return group;
	}
}
