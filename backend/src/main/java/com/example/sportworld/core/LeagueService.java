package com.example.sportworld.core;

import com.example.sportworld.core.models.League;
import com.example.sportworld.repositories.LeagueRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LeagueService {
    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public League createLeague(String name) {
        return Mappers.fromLeagueDAO(leagueRepository.createLeague(name));
    }

    public List<League> listLeagues(int page, int pageSize) {
        return leagueRepository.listLeagues(page, pageSize)
                .stream()
                .map(Mappers::fromLeagueDAO)
                .collect(Collectors.toList());
    }

    public League getLeague (int id) {
        return Mappers.fromLeagueDAO(leagueRepository.getLeague(id));
    }

    public void deleteLeague(int id) {
        leagueRepository.deleteLeague(id);
    }
}

