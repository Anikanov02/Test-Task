package com.test.task.backend.controller;

import com.test.task.backend.domain.dto.ContractDto;
import com.test.task.backend.domain.model.Contract;
import com.test.task.backend.service.ContractService;
import com.test.task.backend.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final UserPermissionService permissionService;

    @PostMapping("new")
    public ResponseEntity<?> newContract(@RequestBody @Valid ContractDto contractData, BindingResult bindingResult, Principal auth) {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream().reduce("", (subMessage, err) -> subMessage + " " + err.getDefaultMessage(), String::concat);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        final Contract contract = contractService.createContract(contractData, auth.getName());
        log.info("created contract, new id: {}", contract.getId());
        return new ResponseEntity<>(ContractDto.convert(contract), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("data/{contractId}")
    public ResponseEntity<?> getContractData(@PathVariable long contractId, Principal auth) {
        if (permissionService.ownsContract(contractId, auth.getName())) {
            return new ResponseEntity<>(ContractDto.convert(contractService.getContractById(contractId)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("edit/{contractId}")
    public ResponseEntity<?> editContractData(@PathVariable long contractId, @RequestBody @Valid ContractDto contractData, BindingResult bindingResult, Principal auth) {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream().reduce("", (subMessage, err) -> subMessage + " " + err.getDefaultMessage(), String::concat);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        if (permissionService.ownsContract(contractId, auth.getName())) {
            final Contract updated = contractService.updateContract(contractId, contractData);
            log.info("updated contract data, id: {}", contractId);
            return new ResponseEntity<>(ContractDto.convert(updated), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("delete/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable long contractId, Principal auth) {
        if (permissionService.ownsContract(contractId, auth.getName())) {
            contractService.deleteContract(contractId);
            log.info("deleted contract, id: {}", contractId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("archive/{contractId}")
    public ResponseEntity<?> archiveContractData(@PathVariable long contractId, Principal auth) {
        if (permissionService.ownsContract(contractId, auth.getName())) {
            final Contract archived = contractService.archiveContract(contractId);
            log.info("archived contract, id: {}", contractId);
            return new ResponseEntity<>(ContractDto.convert(archived), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
