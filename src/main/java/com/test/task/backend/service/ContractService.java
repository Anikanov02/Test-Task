package com.test.task.backend.service;

import com.test.task.backend.domain.dto.ContractDto;
import com.test.task.backend.domain.model.Contract;
import com.test.task.backend.domain.model.User;
import com.test.task.backend.repository.ContractRepository;
import com.test.task.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContractService {
    private final ContractRepository contractRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    public Contract createContract(ContractDto contractData, String auth) {
        final Contract contract = Contract.builder()
                .user(userService.getUserByEmail(auth))
                .subscriptionDate(contractData.getSubscriptionDate())
                .startDate(contractData.getStartDate())
                .expirationDate(contractData.getExpirationDate())
                .sumInsured(contractData.getSumInsured())
                .contractSum(contractData.getContractSum())
                .isArchived(contractData.getIsArchived())
                .build();
        return contractRepository.save(contract);
    }

    public Contract getContractById(long contractId) {
        return contractRepository.findById(contractId).orElseThrow(() -> new RuntimeException(String.format("No contract found with id %d", contractId)));
    }

    public Contract updateContract(long contractId, ContractDto contractData) {
        final Contract contract = getContractById(contractId);
        contract.setSubscriptionDate(contractData.getSubscriptionDate());
        contract.setStartDate(contractData.getStartDate());
        contract.setExpirationDate(contractData.getExpirationDate());
        contract.setSumInsured(contractData.getSumInsured());
        contract.setContractSum(contractData.getContractSum());
        contract.setIsArchived(contractData.getIsArchived());
        return contractRepository.save(contract);
    }

    public void deleteContract(long contractId, String auth) {
        final User user = userService.getUserByEmail(auth);
        user.getContracts().removeIf(contract -> contract.getId().equals(contractId));
        userRepository.save(user);
    }

    public Contract archiveContract(long contractId) {
        final Contract contract = getContractById(contractId);
        if (contract.getIsArchived()) {
            throw new RuntimeException(String.format("Contract with id %s is already archived", contractId));
        } else {
            contract.setIsArchived(true);
            return contractRepository.save(contract);
        }
    }
}
