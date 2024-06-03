package com.sportworld.web.api;

import com.sportworld.bin.web.api.LeagueController;
import com.sportworld.core.LeagueService;
import com.sportworld.core.models.League;
import com.sportworld.repositories.LeagueRepository;
import com.sportworld.repositories.models.LeagueDAO;
import com.sportworld.bin.web.api.models.LeagueInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LeagueControllerTest {
    private LeagueRepository leagueRepository;
    private LeagueService leagueService;
    private LeagueController leagueController;
    private final LeagueDAO leagueTemplate = new LeagueDAO(1, "league");

    @BeforeEach
    public void setUp() {
        leagueRepository = Mockito.mock(LeagueRepository.class);
        leagueService = new LeagueService(leagueRepository);
        leagueController = new LeagueController(leagueService);
    }

    @Test
    void createLeagueTest() {
        when(leagueRepository.createLeague(anyString())).thenReturn(leagueTemplate);
        LeagueInput leagueInput = new LeagueInput("league");

        ResponseEntity<?> response = leagueController.createLeague(leagueInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getLeagueTest () {
        when(leagueRepository.getLeague(anyInt())).thenReturn(leagueTemplate);

        ResponseEntity<?> response = leagueController.getLeague(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listLeaguesTest() {
        List<LeagueDAO> leagues = new ArrayList<>();
        leagues.add(leagueTemplate);
        when(leagueRepository.listLeagues(anyInt(), anyInt())).thenReturn(leagues);


        List<League> response = leagueController.listLeagues(0, 1);


        assertEquals(leagues.size(), response.size());
        for (int i = 0; i < leagues.size(); i++) {
            assertEquals(leagues.get(i).id(), response.get(i).id());
            assertEquals((leagues.get(i).name()), response.get(i).name());
        }
    }

    @Test
    void deleteCommentTest() {

        leagueController.deleteLeague(1);

        verify(leagueRepository, times(1)).deleteLeague(anyInt());

    }
}
