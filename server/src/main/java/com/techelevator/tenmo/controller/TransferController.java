package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.checker.TransferChecks;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private TransferDao dao;
    private AccountDao accountDao;
    private UserDao userDao;
    private TransferChecks checks;

    public TransferController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.dao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.checks = new TransferChecks(transferDao, accountDao, userDao);
    }

    //todo need to make sure it's just pulling transfers by the user that has their token in -- ask if this needs to be in the dto
    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfer(Principal principal) {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        List<Transfer> transfers = dao.getAllTransfer(userId);
        if (!principal.equals(username)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No transfers");
        } else {
            return transfers;
        }
    }

    //todo: this is not working in postman
    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public Transfer getTransfer(Principal principal) {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        Transfer transfer = dao.getTransfer(userId);
        if (!principal.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return transfer;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transfer")
    public void sendTransfer(@RequestBody Transfer transfer, Principal principal) {
        if (!transfer.getFromUsername().equals(principal)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't access"); }
            checks.performTransfer(transfer);
    }

    //todo this is not working in postman
    @RequestMapping(path = "/transfer/{status}", method = RequestMethod.GET)
    public List<Transfer> getPendingStatus(@RequestParam(defaultValue = "") String status, Principal principal) {
        List<Transfer> transferList = null;
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        return dao.getPendingStatus(status);
    }

}


//    @RequestMapping(path = "/transfer/{status}", method = RequestMethod.GET)
//    public Transfer getStatus(Principal principal, @PathVariable ) {
//        Transfer transfer = dao.getTransfer(id);
//        if (transfer == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        } else {
//            return transfer;
//        }
//    }
