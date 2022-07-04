package com.sportworld.repositories;

import com.sportworld.repositories.models.MatchDAO;

import java.util.List;

public interface MatchRepository {
    MatchDAO createMatch(String title, String content, int leagueID, int userID);

    MatchDAO getMatch(int id);

    List<MatchDAO> listMatches(int page, int pageSize);

    void deleteMatch(int id);
}
