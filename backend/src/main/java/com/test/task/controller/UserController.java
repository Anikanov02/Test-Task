package com.test.task.controller;

import com.test.task.domain.dto.ContractDto;
import com.test.task.domain.dto.LoginDto;
import com.test.task.domain.dto.UserDto;
import com.test.task.domain.model.User;
import com.test.task.client.service.SecurityService;
import com.test.task.client.service.UserPermissionService;
import com.test.task.client.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserPermissionService permissionService;

    private final SecurityService securityService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult, Principal auth) {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream().reduce("", (subMessage, err) -> subMessage + " " + err.getDefaultMessage(), String::concat);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        securityService.login(loginDto.getLogin(), loginDto.getPassword());
        log.debug("logged new user: " + loginDto.getLogin());
        return new ResponseEntity<>(UserDto.convert(userService.getUserByEmail(loginDto.getLogin())), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<?> newUser(@RequestBody @Valid UserDto userData, BindingResult bindingResult, Principal auth) {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream().reduce("", (subMessage, err) -> subMessage + " " + err.getDefaultMessage(), String::concat);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        final User user = userService.createUser(userData);
        log.info("created new user, id={}, login={}", user.getId(), user.getEmail());
        securityService.login(user.getEmail(), userData.getPassword());
        return new ResponseEntity<>(UserDto.convert(user), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("data/{userId}")
    public ResponseEntity<?> getUserData(@PathVariable long userId, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            return new ResponseEntity<>(UserDto.convert(userService.getUserById(userId)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("{userId}/contracts")
    public ResponseEntity<?> getContracts(@PathVariable long userId, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            return new ResponseEntity<>(userService.getUserById(userId).getContracts().stream().map(ContractDto::convert), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("edit/{userId}")
    public ResponseEntity<?> editUserData(@PathVariable long userId, @RequestBody @Valid UserDto userData, BindingResult bindingResult, Principal auth) {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream().reduce("", (subMessage, err) -> subMessage + " " + err.getDefaultMessage(), String::concat);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        if (permissionService.isAuthenticated(userId, auth.getName())) {
            return new ResponseEntity<>(UserDto.convert(userService.updateUser(userId, userData)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            userService.deleteUser(userId);
            log.info("deleted user, id={}", userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
