
package com.test.task.domain.dto;

import com.test.task.domain.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto {
    private Long id;

    @NotNull(message = "subscriptionDate must not be null")
    private LocalDate subscriptionDate;

    @NotNull(message = "startDate must not be null")
    private LocalDate startDate;

    @NotNull(message = "expirationDate must not be null")
    private LocalDate expirationDate;

    @Min(value = 0, message = "sumInsured should be > 0")
    @NotNull(message = "sumInsured must not be null")
    private BigDecimal sumInsured;

    @Min(value = 0, message = "contractSum should be > 0")
    @NotNull(message = "contractSum must not be null")
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
