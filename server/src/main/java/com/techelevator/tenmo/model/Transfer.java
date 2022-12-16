package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transfer {
    @JsonProperty("transfer_id")
    private int id;
    @JsonProperty("date_and_time")
    private LocalDate date;
    @JsonProperty("from_username")
    private String fromUsername;
    @JsonProperty("from_user_id")
    private int fromUser;
    @JsonProperty("to_username")
    private String toUsername;
    @JsonProperty("to_user_id")
    private int toUser;
    @JsonProperty("transfer_amount")
    private BigDecimal transferAmount;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", date=" + date +
                ", fromUsername='" + fromUsername + '\'' +
                ", fromUser=" + fromUser +
                ", toUsername='" + toUsername + '\'' +
                ", toUser=" + toUser +
                ", transferAmount=" + transferAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
