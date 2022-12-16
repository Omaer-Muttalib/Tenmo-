package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {

    //todo: remove userId from these. they need to pass in what the client inputs (which will be username and switched in the dto)
    BigDecimal findBalance(int userId);

    BigDecimal addToBalance(BigDecimal amountToAdd, int userId);

    BigDecimal subtractFromBalance(BigDecimal amountToSubtract, int userId);
}
