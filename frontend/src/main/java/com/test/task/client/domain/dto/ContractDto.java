
package com.test.task.client.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto {
    private Long id;

    private String subscriptionDate;

    private String startDate;

    private String expirationDate;

    private BigDecimal sumInsured;

    private BigDecimal contractSum;

    private Boolean isArchived;
}
