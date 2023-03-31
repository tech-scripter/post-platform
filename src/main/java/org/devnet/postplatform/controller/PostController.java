package org.devnet.postplatform.controller;

import lombok.RequiredArgsConstructor;
import org.devnet.postplatform.model.Post;
import org.devnet.postplatform.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scramble")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        List<Post> posts = postService.findAll(pageNo, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Post post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
