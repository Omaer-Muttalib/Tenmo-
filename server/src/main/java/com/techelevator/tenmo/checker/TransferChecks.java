package com.techelevator.tenmo.checker;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;


public class TransferChecks {

        TransferDao transferDao;
        AccountDao accountDao;
        UserDao userDao;


    public TransferChecks(TransferDao transferDao, AccountDao accountDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public void performTransfer(Transfer transfer) {
        //1. check transfer is valid, e.g. insufficient funds etc
        if (isTransferValid(transfer)) {//you can use boolean or custom exceptions
            //2. deduct amount from sender
            deductMoney(transfer.getFromUsername(), transfer.getTransferAmount());
            //3. add amount to receiver
            addMoney(transfer.getToUsername(),transfer.getTransferAmount());
            //4. add transaction record to transfer table to db
            addTransferRecord(transfer);
        }
    }

    public boolean isTransferValid(Transfer transfer) {

        if(transfer.getFromUsername().equals(transfer.getToUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't send money to yourself");
        }
        //if username is not found both for to and from
        if(accountDao.findBalance(userDao.findIdByUsername(transfer.getFromUsername())).compareTo(transfer.getTransferAmount()) == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You have no money left");
        }
        if(transfer.getFromUsername().equals(null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must enter an Username");
        }
        if(transfer.getToUsername().equals(null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must enter an Username");
        }
        if(transfer.getTransferAmount().equals(null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must enter an Amount");
        }
        if (transfer.getTransferAmount().compareTo(new BigDecimal("0.01")) == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must enter an Amount greater than 0.00");
        }
        return false;
    }

    private void deductMoney(String sender, BigDecimal amount) {
        // get current balance
        // subtract amount to current money
        //update balance
        int userId = userDao.findIdByUsername(sender);
        accountDao.subtractFromBalance(amount, userId);
    }
    private void addMoney(String receiver, BigDecimal amount) {
        // get current balance
        // add amount to current money
        //update balance
        int userId = userDao.findIdByUsername(receiver);
        accountDao.addToBalance(amount, userId);
    }
    private void addTransferRecord(Transfer transfer) {
        transferDao.createTransfer(transfer);
    }
    private TransferChecks checker;

    public TransferChecks(TransferChecks checker) {
            this.checker = checker;
        }
    }
