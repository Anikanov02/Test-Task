
package com.test.task.domain.dto;

import com.test.task.domain.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto {
    private Long id;

    private LocalDate subscriptionDate;

    private LocalDate startDate;

    private LocalDate expirationDate;

    private BigDecimal sumInsured;

    private BigDecimal contractSum;

    private Boolean isArchived;

    public static ContractDto convert(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .subscriptionDate(contract.getSubscriptionDate())
                .startDate(contract.getStartDate())
                .expirationDate(contract.getExpirationDate())
                .sumInsured(contract.getSumInsured())
                .contractSum(contract.getContractSum())
                .isArchived(contract.getIsArchived())
                .build();
    }
}
