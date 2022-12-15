package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {
    private AccountDao dao;

    public AccountController() {
        this.dao = dao;
    }

    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public BigDecimal findBalance(@PathVariable int id) {
        BigDecimal balance = dao.findBalance(id);
        return balance;
    }

    @RequestMapping(path = "/balance/{id}", method = RequestMethod.PUT)
    public BigDecimal addToBalance(@RequestBody BigDecimal amountToAdd, @PathVariable int id) {
        BigDecimal updateBalance = dao.addToBalance(amountToAdd, id);
        return updateBalance;
    }

    @RequestMapping(path = "/balance/{id}", method = RequestMethod.PUT)
    public BigDecimal subtractFromBalance(@RequestBody BigDecimal amountToSubtract, @PathVariable int id) {
        BigDecimal updateBalance = dao.addToBalance(amountToSubtract, id);
        return updateBalance;

    }
}
