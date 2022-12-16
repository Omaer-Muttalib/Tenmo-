package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {

    //todo: remove userId from these. they need to pass in what the client inputs (which will be username and switched in the dto)
    double findBalance(int userId);

    double addToBalance(double amountToAdd, int userId);

    double subtractFromBalance(double amountToSubtract, int userId);
}
