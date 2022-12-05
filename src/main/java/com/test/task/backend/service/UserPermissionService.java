package com.test.task.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPermissionService {
    private final UserService userService;
    private final ContractService contractService;

    public boolean isAuthenticated(long userId, String auth) {
        if (userService.getUserById(userId) == null) {
            return false;
        }

        return userService.getUserById(userId).equals(userService.getUserByEmail(auth));
    }

    public boolean ownsContract(long contractId, String auth) {
        return userService.getUserByEmail(auth).getContracts().stream().anyMatch(contract -> contract.getId().equals(contractId));
    }
}
