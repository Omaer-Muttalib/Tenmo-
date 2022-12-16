package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.security.Principal;
import java.util.List;

public interface TransferDao {
    //todo: remove the id and do this in the dto
    public List<Transfer> getAllTransfer(int userId);
    public Transfer getTransfer(int id);
    public Transfer sendTransfer(Transfer newTransfer);
//    public Transfer getStatus(int id);
    public List<Transfer> getPendingStatus(String status);
}
