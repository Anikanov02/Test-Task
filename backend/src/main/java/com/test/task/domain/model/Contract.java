package com.test.task.domain.model;

import com.test.task.domain.converter.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract {
    @Id
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}
