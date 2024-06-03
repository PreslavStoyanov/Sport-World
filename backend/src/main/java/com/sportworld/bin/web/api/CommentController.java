package com.sportworld.bin.web.api;

import com.sportworld.bin.web.api.models.CommentInput;
import com.sportworld.core.CommentService;
import com.sportworld.core.UserService;
import com.sportworld.core.models.Comment;
import com.sportworld.core.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/comments")
public class CommentController {
    public final CommentService commentService;
    public final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentInput comment, @RequestHeader("Authorization") String token) {
        User user = userService.getUserInfoByToken(token);
        try {
            return new ResponseEntity<>(commentService.createComment(comment.content, user.getId(), comment.matchID), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer id) {
        try {
            Comment match = commentService.getComment(id);
            return new ResponseEntity<>(match, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Comment> listComments(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return commentService.listComments(page, pageSize);
    }

    @GetMapping(value = "/matches/{matchID}")
    public List<Comment> listCommentsByMatch(@PathVariable Integer matchID) {
        return commentService.listCommentsByMatch(matchID);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}
