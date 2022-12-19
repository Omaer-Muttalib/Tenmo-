package com.techelevator.dao;

import com.techelevator.tenmo.model.Transfer;
import org.junit.Test;

public class JdbcTransferDaoTests {

    //todo: integrations tets

    @Test
    public void getTransferTest() {
        Transfer transfer1 = sut.getTransferbyId(0);

    }

}
