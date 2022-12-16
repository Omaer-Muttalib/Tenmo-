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
    public BigDecimal findBalance(int userId) {
        BigDecimal balance = null;
        String sql = "SELECT balance FROM account WHERE user_id =?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if(result.next()){
            balance = result.getBigDecimal("balance");
        }
        return balance;
    }

    @Override
    public BigDecimal addToBalance(BigDecimal amountToAdd, int userId) {
        Account account = new Account();
        BigDecimal addToBalance = account.getBalance();
        String sql = "UPDATE account SET balance WHERE user_id = ? ";
      //todo put try/catch statement here if code doesnt work
        jdbcTemplate.update(sql, amountToAdd, userId);
        return addToBalance;
    }

    @Override
    public BigDecimal subtractFromBalance(BigDecimal amountToSubtract, int userId) {
        Account account = new Account();
        BigDecimal subtractFromBalance = account.getBalance();
        String sql = "UPDATE account SET balance WHERE user_id = ? ";
        //todo put try/catch statement here if code doesnt work
        jdbcTemplate.update(sql, amountToSubtract, userId);
        return subtractFromBalance;
    }

    private Account mapToRowSet(SqlRowSet accountRowSet) {
        Account account = new Account();
        account.setBalance(accountRowSet.getBigDecimal("balance"));
        account.setId(accountRowSet.getInt("id"));
        account.setUserId(accountRowSet.getInt("userId"));

        return account;
    }

}
