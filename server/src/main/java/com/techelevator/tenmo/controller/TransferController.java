package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
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

    public TransferController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.dao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    //todo: this is not working in postman
    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public Transfer getTransfer(Principal principal) {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        Transfer transfer = dao.getTransfer(userId);
        if (username.equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return transfer;
        }
    }

    //todo need to make sure it's just pulling transfers by the user that has their token in -- ask if this needs to be in the dto
    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfer(Principal principal) {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        List<Transfer> transfers = dao.getAllTransfer(userId);
        return transfers;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transfer")
    public void sendTransfer(@RequestBody Transfer transfer) {
        dao.sendTransfer(transfer);
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