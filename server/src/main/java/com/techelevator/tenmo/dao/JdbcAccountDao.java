package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public double findBalance(int userId) {
        double balance = 0;
        String sql = "SELECT balance FROM account WHERE user_id =?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if(result.next()){
            balance = result.getDouble("balance");
        }
        return balance;
    }

    @Override
    public double addToBalance(double amountToAdd, int userId) {
        Account account = new Account();
        double addToBalance = account.getBalance();
        String sql = "UPDATE account SET balance WHERE user_id = ? ";
      //todo put try/catch statement here if code doesnt work
        jdbcTemplate.update(sql, amountToAdd, userId);
        return addToBalance;
    }

    @Override
    public double subtractFromBalance(double amountToSubtract, int userId) {
        Account account = new Account();
        double subtractFromBalance = account.getBalance();
        String sql = "UPDATE account SET balance WHERE user_id = ? ";
        //todo put try/catch statement here if code doesnt work
        jdbcTemplate.update(sql, amountToSubtract, userId);
        return subtractFromBalance;
    }

    private Account mapToRowSet(SqlRowSet accountRowSet) {
        Account account = new Account();
        account.setBalance(accountRowSet.getDouble("balance"));
        account.setId(accountRowSet.getInt("id"));
        account.setUserId(accountRowSet.getInt("userId"));



        return account;
    }

}
