///package com.techelevator.tenmo.controller;
//
//import com.techelevator.tenmo.dao.UserDao;
//import com.techelevator.tenmo.model.User;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@RestController
////todo if test doesnt pass maybe add preauth @
//public class UserController {
//    private UserDao dao;
//
//    public UserController() {
//        this.dao = dao;
//    }
//
//    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    public List<User> findAllUsers() {
//        return dao.findAll();
//    }
//
//    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
//    public User findByUsername(@PathVariable String username) {
//        if (username.equals("")) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return dao.findByUsername(username);
//    }
//
//    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
//    public int findId(@PathVariable String username) {
//        if (username.equals("")) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return dao.findIdByUsername(username);
//
//    }
//    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    public boolean userCreated(){
//        if(userCreated != user)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return true;
//    }
//}
package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@PreAuthorize("isAuthenticated()")
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

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<String> findAllUsers() {
        List<String> username = new ArrayList<>();
        List<User> users = dao.findAll();
        for (User user : users) {
            username.add(user.getUsername());
        }
        return username;
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
