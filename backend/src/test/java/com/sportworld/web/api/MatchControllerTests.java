package com.sportworld.web.api;

import com.sportworld.bin.web.api.MatchController;
import com.sportworld.core.MatchService;
import com.sportworld.core.UserService;
import com.sportworld.core.models.Match;
import com.sportworld.repositories.MatchRepository;
import com.sportworld.repositories.UserRepository;
import com.sportworld.repositories.models.LeagueDAO;
import com.sportworld.repositories.models.MatchDAO;
import com.sportworld.bin.web.api.models.MatchInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatchControllerTests {
    private MatchController matchController;
    private MatchService matchService;
    private UserService userService;
    private MatchRepository matchRepository;
    private UserRepository userRepository;
    private static final String mockFailToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWUiOiJ1c2VyIiwicm9sZUlkIjoxLCJpYXQiOjE2NTU0NzIyOTEsImV4cCI6MTY1NTUzMjI5MX0.7mIC5PZG0OKSTeK0UruIcPPPyPSnVDCkX06HVxkAwBZQ4dyYR_jSDpFnt5rBdtnpOxx8Nxd_bYI3SKmjRKP9zw";
    private static final String mockSuccessToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWUiOiJQcmVzbGF2Iiwicm9sZUlkIjoyLCJpYXQiOjE2NTU2MjYyMTAsImV4cCI6MTY1NTY4NjIxMH0.8lA27bSoOZwRRK3FwSstk0dhj83E3vD81WjB7xvytcqEJoxewDV86aIX_KwL6I0OYtZ5KSMkQkVHu8zrm7hwkg";
    private final MatchDAO template = new MatchDAO(1, "title", "content",
            null, new LeagueDAO(1, "league"), 1);

    @BeforeEach
    public void setUp() {
        matchRepository = Mockito.mock(MatchRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        matchService = new MatchService(matchRepository);
        userService = new UserService(userRepository);
        matchController = new MatchController(matchService, userService);
    }

    @Test
    void createMatchTestSuccess () {
        when(matchRepository.createMatch(anyString(), anyString(), anyInt(), anyInt())).thenReturn(template);
        MatchInput matchInput = new MatchInput("title", "content", 1);

        ResponseEntity<?> response = matchController.createMatch(matchInput, mockSuccessToken);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createMatchTestFail () {
        when(matchRepository.createMatch(anyString(), anyString(), anyInt(), anyInt())).thenReturn(template);
        MatchInput matchInput = new MatchInput("title", "content", 1);

        ResponseEntity<?> response = matchController.createMatch(matchInput, mockFailToken);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void getMatchTest () {
        when(matchRepository.getMatch(anyInt())).thenReturn(template);

        ResponseEntity<?> response = matchController.getMatch(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listMatchesTest() {
        List<MatchDAO> matches = new ArrayList<>();
        matches.add(template);
        when(matchRepository.listMatches(anyInt(), anyInt())).thenReturn(matches);


        List<Match> response = matchController.listMatches(0, 1);


        assertEquals(matches.size(), response.size());
        for (int i = 0; i < matches.size(); i++) {
            assertEquals(matches.get(i).id(), response.get(i).id());
            assertEquals((matches.get(i).title()), response.get(i).title());
            assertEquals((matches.get(i).content()), response.get(i).content());
        }
    }

    @Test
    void deleteMatchTest() {
        matchController.deleteMatch(1);

        verify(matchRepository, times(1)).deleteMatch(anyInt());

    }
}
