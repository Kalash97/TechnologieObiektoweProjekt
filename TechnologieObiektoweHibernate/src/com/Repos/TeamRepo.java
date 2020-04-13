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
	
	public void deleteTeam(Team team) {
		hpm.delete(team);
	}
	
	public Team findById(long id) {
		return (Team) hpm.findById(id, Team.class);
	}
}
