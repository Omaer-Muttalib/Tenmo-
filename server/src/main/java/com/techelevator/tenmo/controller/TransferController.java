package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
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
        this.dao = dao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }


    //todo ask about the endpoints//
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int id) {
//        String username = principal.getName();
//        int userId = userDao.findIdByUsername(username);
        Transfer transfer = dao.getTransfer(id);
//        if (username.equals("")) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        } else {
//            return transfer;
//        }
        return transfer;
    }


//    //todo check to see if we need accountId or not || check the path if its {id} or not
//    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
//    public List<Transfer> getAllTransfer(Principal principal) {
//        String username = principal.getName();
//        int userId = userDao.findIdByUsername(username);
//        return dao.getAllTransfer(userId);
//    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transfer")
    public void sendTransfer(@RequestBody Transfer transfer) {
//        dao.sendTransfer(transfer);
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

    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getPendingStatus(@RequestParam(defaultValue = "") String status_like, Principal principal) {
        List<Transfer> transferList = null;
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        return dao.getPendingStatus(status_like);
    }

}
