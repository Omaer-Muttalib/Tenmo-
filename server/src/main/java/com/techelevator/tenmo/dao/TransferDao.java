package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.security.Principal;
import java.util.List;

public interface TransferDao {

    public List<Transfer> getAllTransfer();
    public Transfer getTransfer(int id);
    public void sendTransfer(Transfer newTransfer);
//    public Transfer getStatus(int id);
    public List<Transfer> getPendingStatus(String status);
}
