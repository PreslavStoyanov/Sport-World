package com.example.sportworld.web.api;

import com.example.sportworld.core.MatchService;
import com.example.sportworld.core.UserService;
import com.example.sportworld.core.models.Match;
import com.example.sportworld.repositories.MatchRepository;
import com.example.sportworld.repositories.UserRepository;
import com.example.sportworld.repositories.models.LeagueDAO;
import com.example.sportworld.repositories.models.MatchDAO;
import com.example.sportworld.web.api.models.MatchInput;
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

public class MatchControllerTests {
    private MatchController matchController;
    private MatchService matchService;
    private UserService userService;
    private MatchRepository matchRepository;
    private UserRepository userRepository;
    private static final String mockFailToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViI" +
            "joiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWUiOiJ1c2V" +
            "yIiwicm9sZV9pZCI6MSwiaWF0IjoxNjU1NDcyMjkxLCJleHAiOjE2NTU1MzIyOTF9.PGuBzVg43t6sFFXv" +
            "fU4_blDxVlZOsN2XIog-E5G5vM125lH-kSIse3bjB5bJxWI-7cX_vQpSaUoEAzcUzFhXrw";
    private static final String mockSuccessToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiw" +
            "ic3ViIjoiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWU" +
            "iOiJQcmVzbGF2Iiwicm9sZV9pZCI6MiwiaWF0IjoxNjU1NjI2MjEwLCJleHAiOjE2NTU2ODYyMTB9.v0" +
            "CmSwpHCxJurXjYJTy6VNG7FeXLN4yfvczkZ2Ox-QLZE99U5Je9s5ruzZqYWtf7oY1-9NcfQApfJdggIw7Vhg";
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
    public void createMatchTestSuccess () {
        when(matchRepository.createMatch(anyString(), anyString(), anyInt(), anyInt())).thenReturn(template);
        MatchInput matchInput = new MatchInput("title", "content", 1);

        ResponseEntity<?> response = matchController.createMatch(matchInput, mockSuccessToken);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createMatchTestFail () {
        when(matchRepository.createMatch(anyString(), anyString(), anyInt(), anyInt())).thenReturn(template);
        MatchInput matchInput = new MatchInput("title", "content", 1);

        ResponseEntity<?> response = matchController.createMatch(matchInput, mockFailToken);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getMatchTest () {
        when(matchRepository.getMatch(anyInt())).thenReturn(template);

        ResponseEntity<?> response = matchController.getMatch(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listMatchesTest() {
        List<MatchDAO> matches = new ArrayList<>();
        matches.add(template);
        when(matchRepository.listMatches(anyInt(), anyInt())).thenReturn(matches);


        List<Match> response = matchController.listMatches(0, 1);


        assertEquals(matches.size(), response.size());
        for (int i = 0; i < matches.size(); i++) {
            assertEquals(matches.get(i).id, response.get(i).id);
            assertEquals((matches.get(i).title), response.get(i).title);
            assertEquals((matches.get(i).content), response.get(i).content);
        }
    }

    @Test
    public void deleteMatchTest() {
        matchController.deleteMatch(1);

        verify(matchRepository, times(1)).deleteMatch(anyInt());

    }
}
