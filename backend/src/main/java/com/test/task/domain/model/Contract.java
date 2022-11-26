package com.test.task.domain.model;

import com.test.task.domain.converter.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @Column(name = "subscription_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate subscriptionDate;

    @Column(name = "start_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate startDate;

    @Column(name = "expiration_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate expirationDate;

    @Column(name = "sum_insured")
    private BigDecimal sumInsured;

    @Column(name = "contract_sum")
    private BigDecimal contractSum;

    @Column(name = "is_archived")
    private Boolean isArchived;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}
