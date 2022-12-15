package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    public List<Transfer> getAllTransfer(int accountId);
    public Transfer getTransfer(int id);
    public Transfer sendTransfer(Transfer newTransfer);
    public Transfer getStatus(int id);
    public List<Transfer> getPendingStatus(int accountId);
}
