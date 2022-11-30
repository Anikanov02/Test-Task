package com.test.task.client.domain.dto;

import java.math.BigDecimal;

public class ContractDto {
    private Long id;

    private String subscriptionDate;

    private String startDate;

    private String expirationDate;

    private BigDecimal sumInsured;

    private BigDecimal contractSum;

    private Boolean isArchived;

    public ContractDto(String subscriptionDate, String startDate, String expirationDate, BigDecimal sumInsured, BigDecimal contractSum, Boolean isArchived) {
        this.subscriptionDate = subscriptionDate;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.sumInsured = sumInsured;
        this.contractSum = contractSum;
        this.isArchived = isArchived;
    }

    public ContractDto() {
    }

    public Long getId() {
        return id;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public BigDecimal getContractSum() {
        return contractSum;
    }

    public void setContractSum(BigDecimal contractSum) {
        this.contractSum = contractSum;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }
}
