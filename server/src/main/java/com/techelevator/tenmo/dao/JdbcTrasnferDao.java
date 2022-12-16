package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTrasnferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTrasnferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transfer> getAllTransfer() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while(result.next()) {
            Transfer transfer = mapToRowSet(result);
            transfers.add(transfer);
        }
        return transfers;
    }

    //todo: if statement
    @Override
    public Transfer getTransfer(int id) {
        String sql = "SELECT * FROM transfer WHERE id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        return mapToRowSet(result);
    }

    @Override
    public void sendTransfer(Transfer newTransfer) {
        Transfer sendTransfer = null;
        String sql = "INSERT INTO transfer (date_and_time, from_username, from_user_id, to_username, to_user_id, transfer_amount, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Integer transferId = jdbcTemplate.queryForObject(sql, int.class,
                newTransfer.getDate(),
                newTransfer.getStatus(),
                newTransfer.getFromUser(),
                newTransfer.getToUser(),
                newTransfer.getFromUsername(),
                newTransfer.getToUsername(),
                newTransfer.getTransferAmount());
        sendTransfer = getTransfer(transferId);

    }

//    //todo check this if code doesn't work//
//    @Override
//    public Transfer getStatus(Transfer transfer) {
//        String sql = "SELECT * FROM transfer WHERE id = ?";
//        Transfer transfer2 = null;
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transfer);
//        if(result.next()) {
//            transfer = mapToRowSet(result);
//        }
//        return transfer;
//    }

    @Override
    public List<Transfer> getPendingStatus(String status) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE status iLIKE ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, status);

        while(result.next()) {
            Transfer transfer = mapToRowSet(result);
            transfers.add(transfer);
        }
        return transfers;
    }

    private Transfer mapToRowSet(SqlRowSet transferRowSet) {
        Transfer transfer = new Transfer();
        transfer.setDate(transferRowSet.getDate("date").toLocalDate());
        transfer.setFromUser(transferRowSet.getInt("from_user_id"));
        transfer.setToUser(transferRowSet.getInt("to_user_id"));
        transfer.setFromUsername(transferRowSet.getString("from_username"));
        transfer.setToUsername(transferRowSet.getString("to_username"));
        transfer.setTransferAmount(transferRowSet.getBigDecimal("amount"));
        transfer.setStatus(transferRowSet.getString("status"));
        return transfer;
    }

}
