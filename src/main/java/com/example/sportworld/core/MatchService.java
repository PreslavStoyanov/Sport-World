package com.example.sportworld.core;

import com.example.sportworld.core.models.Match;
import com.example.sportworld.repositories.MatchRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match createMatch(String title, String content, int leagueID) {
        return Mappers.fromMatchDAO(matchRepository.createMatch(title, content, leagueID));
    }

    public List<Match> listMatches(int page, int pageSize) {
        return matchRepository.listMatches(page, pageSize)
                .stream()
                .map(Mappers::fromMatchDAO)
                .collect(Collectors.toList());
    }

    public Match getMatch(int id) {
        return Mappers.fromMatchDAO(matchRepository.getMatch(id));
    }

    public void deleteMatch(int id) {
        matchRepository.deleteMatch(id);
    }
}
