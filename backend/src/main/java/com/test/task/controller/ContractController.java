package com.test.task.controller;

import com.test.task.domain.dto.ContractDto;
import com.test.task.client.service.ContractService;
import com.test.task.client.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final UserPermissionService permissionService;

    @PostMapping("new")
    public ResponseEntity<?> newContract(@RequestBody ContractDto contractData, Principal auth) {
        return new ResponseEntity<>(ContractDto.convert(contractService.createContract(contractData)), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("data/{contractId}")
    public ResponseEntity<?> getContractData(@PathVariable long contractId, Principal auth) {
        if (permissionService.ownsContract(contractId, auth.getName())) {
            return new ResponseEntity<>(ContractDto.convert(contractService.getContractById(contractId)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("edit/{contractId}")
    public ResponseEntity<?> editContractData(@PathVariable long contractId, @RequestBody ContractDto contractData, Principal auth) {
        if (permissionService.ownsContract(contractId, auth.getName())) {
            return new ResponseEntity<>(ContractDto.convert(contractService.updateContract(contractId, contractData)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    @DeleteMapping("delete/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable long contractId, Principal auth) {
        if (permissionService.ownsContract(contractId, auth.getName())) {
            contractService.deleteContract(contractId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("archive/{contractId}")
    public ResponseEntity<?> archiveContractData(@PathVariable long contractId, Principal auth) {
        if (permissionService.ownsContract(contractId, auth.getName())) {
            return new ResponseEntity<>(ContractDto.convert(contractService.archiveContract(contractId)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
