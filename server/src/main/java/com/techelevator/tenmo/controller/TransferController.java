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
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private TransferDao dao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransferController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.dao = dao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    //todo ask about the endpoints//
    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public Transfer getTransfer(Principal principal) {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        Transfer transfer = dao.getTransfer(userId);
//        if (username.equals("")) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        } else {
        return transfer;
    }

    //
//    //todo check to see if we need accountId or not || check the path if its {id} or not
//    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
//    public List<Transfer> getAllTransfer(@PathVariable int accountId) {
//        return dao.getAllTransfer(accountId);
//    }
//
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer sendTransfer(@RequestBody Transfer transfer) {
        return dao.sendTransfer(transfer);
    }
//
//    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
//    public Transfer getStatus(@PathVariable int id) {
//        Transfer transfer = dao.getTransfer(id);
//        if (transfer == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        } else {
//            return transfer;
//        }
//    }
//    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
//    public List<Transfer> getPendingStatus(@RequestParam(defaultValue = "") String status_like, int accountId) {
//        return dao.getAllTransfer(accountId);
//    }
//
}
