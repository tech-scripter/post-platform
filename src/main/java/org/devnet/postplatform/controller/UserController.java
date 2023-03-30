package org.devnet.postplatform.controller;

import lombok.RequiredArgsConstructor;
import org.devnet.postplatform.model.User;
import org.devnet.postplatform.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scramble")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        List<User> users = userService.findAll(pageNo, pageSize);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
