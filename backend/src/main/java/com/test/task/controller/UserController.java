package com.test.task.controller;

import com.test.task.domain.dto.LoginDto;
import com.test.task.domain.dto.UserDto;
import com.test.task.domain.model.User;
import com.test.task.service.SecurityService;
import com.test.task.service.UserPermissionService;
import com.test.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserPermissionService permissionService;

    private final SecurityService securityService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, Principal auth) {
        securityService.login(loginDto.getLogin(), loginDto.getPassword());
        return new ResponseEntity<>(UserDto.convert(userService.getUserByEmail(loginDto.getLogin())), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<?> newUser(@RequestBody UserDto userData, Principal auth) {
        final User user = userService.createUser(userData);
        securityService.login(user.getEmail(), userData.getPassword());
        return new ResponseEntity<>(UserDto.convert(user), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("data/{userId}")
    public ResponseEntity<?> getUserData(@RequestParam long userId, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            return new ResponseEntity<>(UserDto.convert(userService.getUserById(userId)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PatchMapping("edit/{userId}")
    public ResponseEntity<?> editUserData(@RequestParam long userId, @RequestBody UserDto userData, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            return new ResponseEntity<>(UserDto.convert(userService.updateUser(userId, userData)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<?> deleteUser(@RequestParam long userId, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
