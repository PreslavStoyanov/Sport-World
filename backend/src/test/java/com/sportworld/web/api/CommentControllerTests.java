package com.sportworld.web.api;

import com.sportworld.bin.web.api.CommentController;
import com.sportworld.core.CommentService;
import com.sportworld.core.UserService;
import com.sportworld.core.models.Comment;
import com.sportworld.repositories.CommentRepository;
import com.sportworld.repositories.UserRepository;
import com.sportworld.repositories.models.CommentDAO;
import com.sportworld.repositories.models.UserDAO;
import com.sportworld.bin.web.api.models.CommentInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.*;


public class CommentControllerTests {
    private CommentController commentController;
    private CommentService commentService;
    private UserService userService;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private final UserDAO userTemplate = new UserDAO(1, "user",
            "psd", "user@abv.com", null, null, "123", 1);
    private final CommentDAO commentTemplate = new CommentDAO(1, "cont", null, 1, 1);
    private static final String mockToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiw" +
            "ic3ViIjoiUHJlc2xhdiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWU" +
            "iOiJQcmVzbGF2Iiwicm9sZV9pZCI6MiwiaWF0IjoxNjU1NjI2MjEwLCJleHAiOjE2NTU2ODYyMTB9.v0" +
            "CmSwpHCxJurXjYJTy6VNG7FeXLN4yfvczkZ2Ox-QLZE99U5Je9s5ruzZqYWtf7oY1-9NcfQApfJdggIw7Vhg";

    @BeforeEach
    public void setUp() {
        commentRepository = Mockito.mock(CommentRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        commentService = new CommentService(commentRepository, userRepository);
        userService = new UserService(userRepository);
        commentController = new CommentController(commentService, userService);
    }

    @Test
    public void createCommentTest() {
        when(commentRepository.createComment(anyString(), anyInt(), anyInt())).thenReturn(commentTemplate);
        CommentInput commentInput = new CommentInput("cont", 1);

        ResponseEntity<?> response = commentController.createComment(commentInput, mockToken);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listCommentsTest() {
        List<CommentDAO> comments = new ArrayList<>();
        comments.add(commentTemplate);
        when(commentRepository.listComments(anyInt(), anyInt())).thenReturn(comments);


        List<Comment> response = commentController.listComments(0, 1);


        assertEquals(comments.size(), response.size());
        for (int i = 0; i < comments.size(); i++) {
            assertEquals(comments.get(i).id, response.get(i).id);
            assertEquals((comments.get(i).content), response.get(i).content);
        }
    }

    @Test
    public void listCommentsByMatchTest() {
        List<CommentDAO> comments = new ArrayList<>();
        comments.add(commentTemplate);
        when(commentRepository.listCommentsByMatch(anyInt())).thenReturn(comments);
        when(userRepository.getUserByID(anyInt())).thenReturn(userTemplate);

        List<Comment> response = commentController.listCommentsByMatch(1);


        assertEquals(comments.size(), response.size());
        for (int i = 0; i < comments.size(); i++) {
            assertEquals(comments.get(i).id, response.get(i).id);
            assertEquals((comments.get(i).content), response.get(i).content);
        }
    }

    @Test
    public void getCommentTest () {
        when(commentRepository.getComment(anyInt())).thenReturn(commentTemplate);

        ResponseEntity<?> response = commentController.getComment(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteCommentTest() {
        when(commentRepository.getComment(anyInt())).thenReturn(commentTemplate);

        commentController.deleteComment(1);

        verify(commentRepository, times(1)).deleteComment(anyInt());

    }
}
