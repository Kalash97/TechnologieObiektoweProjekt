package com.Repos;

import com.Entities.Team;
import com.PersistanceManager.HibernatePersistanceManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamRepo {

	HibernatePersistanceManager hpm;
	
	public Team createTeam(Team team) {
		return (Team) hpm.create(team);
	}
	
	public void deleteTeam(long id) {
		hpm.delete(id, Team.class);
	}
}
