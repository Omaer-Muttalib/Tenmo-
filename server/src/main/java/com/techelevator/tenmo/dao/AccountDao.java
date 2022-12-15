package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {
    double findBalance(int userId);

    double addToBalance(double amountToAdd, int userId);

    double subtractFromBalance(double amountToSubtract, int userId);
}
