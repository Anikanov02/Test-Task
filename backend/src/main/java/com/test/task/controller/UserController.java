package com.test.task.controller;

import com.test.task.domain.dto.UserDto;
import com.test.task.service.ContractService;
import com.test.task.service.UserPermissionService;
import com.test.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ContractService contractService;
    private final UserPermissionService permissionService;

    @GetMapping("data/{userId}")
    public ResponseEntity<?> getUserData(@RequestParam long userId, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            return new ResponseEntity<>(UserDto.convert(userService.getUserById(userId)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("edit/{userId}")
    public ResponseEntity<?> editUserData(@RequestParam long userId, Principal auth) {
        if (permissionService.isAuthenticated(userId, auth.getName())) {
            return new ResponseEntity<>(UserDto.convert(userService.getUserById(userId)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
