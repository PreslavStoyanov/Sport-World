package com.example.sportworld.web.api;

import com.example.sportworld.core.LeagueService;
import com.example.sportworld.core.models.League;
import com.example.sportworld.web.api.models.LeagueInput;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/leagues")
public class LeagueController {
    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping
    public ResponseEntity<League> createLeague(@RequestBody LeagueInput leagueInput) {
        try {
            League league = leagueService.createLeague(leagueInput.name);
            return new ResponseEntity<>(league, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<League> getLeague(@PathVariable Integer id) {
        try {
            League league = leagueService.getLeague(id);
            return new ResponseEntity<>(league, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<League> listLeagues(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return leagueService.listLeagues(page, pageSize);
    }

    @DeleteMapping(value = "{id}")
    public void deleteLeague(@PathVariable Integer id) {
        leagueService.deleteLeague(id);
    }

}
