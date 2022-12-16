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

    public UserController(AccountDao accountDao, UserDao dao, TransferDao transferDao) {
        this.dao = dao;
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    //todo: only want to return usernames
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return dao.findAll();
    }

//    //todo: are these two below duplicates?
//    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
//    public User findByUsername(@PathVariable String username) {
//        if (username.equals("")) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return dao.findByUsername(username);
//    }

    //todo: can we endpoint {username}
    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public int findId(@PathVariable String username) {
        if (username.equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return dao.findIdByUsername(username);

    }
    //todo: works springboot but not postman
//    @RequestMapping(path = "/user", method = RequestMethod.POST)
//    public boolean userCreated(@RequestBody User user){
//        if(!userCreated(user)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return true;
//    }
}
