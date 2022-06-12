package com.example.sportworld.web.api;

import com.example.sportworld.core.MatchService;
import com.example.sportworld.core.models.Match;
import com.example.sportworld.web.api.models.MatchInput;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/matches")
public class MatchController {
    public final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public Match createMatch(@RequestBody MatchInput match) {
        return matchService.createMatch(match.title, match.content, match.leagueID);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable Integer id) {
        try {
            Match match = matchService.getMatch(id);
            return new ResponseEntity<>(match, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Match> listMatches(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return matchService.listMatches(page, pageSize);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMatch(@PathVariable Integer id) {
        matchService.deleteMatch(id);
    }
}
