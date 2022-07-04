package com.sportworld.repositories;

import com.sportworld.repositories.models.LeagueDAO;

import java.util.List;

public interface LeagueRepository {

    LeagueDAO createLeague (String name);

    LeagueDAO getLeague(int id);

    List<LeagueDAO> listLeagues(int page, int pageSize);

    void deleteLeague(int id);
}
