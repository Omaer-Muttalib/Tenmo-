package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
//todo if test doesnt pass maybe add preauth @
public class UserController {
    private UserDao dao;
    private TransferDao transferDao;
    private AccountDao accountDao;

    public UserController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.dao = userDao;
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    //todo: only want to return usernames
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        //String username = get.user_id;
        //user dto
        //loop that makes userdto objects
        return dao.findAll();
    }

    //todo: do we need this?
    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public User findByUsername(@RequestBody User users, @PathVariable String username) {
        if (username.equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return dao.findByUsername(username);
        //user dto
        //loop that makes userdto objects
    }

}
