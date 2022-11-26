package com.test.task.service;

import com.test.task.domain.dto.ContractDto;
import com.test.task.domain.model.Contract;
import com.test.task.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

    public void createContract(ContractDto contractData) {
        final Contract contract = Contract.builder()
                .id(contractData.getId())
                .subscriptionDate(contractData.getSubscriptionDate())
                .startDate(contractData.getStartDate())
                .expirationDate(contractData.getExpirationDate())
                .sumInsured(contractData.getSumInsured())
                .contractSum(contractData.getContractSum())
                .isArchived(contractData.getIsArchived())
                .build();
        contractRepository.save(contract);
    }

    public Contract getContractById(long contractId) {
        return contractRepository.findById(contractId).orElseThrow(() -> new RuntimeException(String.format("No contract found with id %d", contractId)));
    }

    public void updateContract(long contractId, ContractDto contractData) {
        final Contract contract = getContractById(contractId);
        contractRepository.save(contract);
    }

    public void deleteContract(long contractId) {
        contractRepository.deleteById(contractId);
    }

    public void archiveContract(long contractId) {
        final Contract contract = getContractById(contractId);
        if (contract.getIsArchived()) {
            throw new RuntimeException(String.format("Contract with id %s is already archived", contractId));
        } else {
            contract.setIsArchived(false);
            contractRepository.save(contract);
        }
    }
}
