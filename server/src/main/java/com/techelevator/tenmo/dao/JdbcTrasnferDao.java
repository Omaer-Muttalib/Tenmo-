package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTrasnferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTrasnferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transfer> getAllTransfer(int accountId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);

        while(result.next()) {
            Transfer transfer = mapToRowSet(result);
            transfers.add(transfer);
        }
        return transfers;
    }


    @Override
    public Transfer getTransfer(int id) {
        String sql = "SELECT * FROM transfer WHERE id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        return mapToRowSet(result);
    }

    @Override
    public Transfer sendTransfer(Transfer newTransfer) {
        Transfer sendTransfer = null;
        String sql = "INSERT INTO transfer (fromUser, toUser, transferAmount) VALUES (?, ?, ?)";
        Integer transferId = jdbcTemplate.queryForObject(sql, int.class, newTransfer.getAccountId(), newTransfer.getDate(), newTransfer.getStatus(), newTransfer.getFromUser(), newTransfer.getToUser(), newTransfer.getTransferAmount());
        sendTransfer = getTransfer(transferId);
        return sendTransfer;
    }

    //todo check this if code doesn't work//
    @Override
    public Transfer getStatus(int id) {
        String sql = "SELECT * FROM transfer WHERE id = ?";
        Transfer transfer = null;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()) {
            transfer = mapToRowSet(result);
        }
        return transfer;
    }

    @Override
    public List<Transfer> getPendingStatus(int accountId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE status iLIKE ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);

        while(result.next()) {
            Transfer transfer = mapToRowSet(result);
            transfers.add(transfer);
        }
        return transfers;
    }

    private Transfer mapToRowSet(SqlRowSet transferRowSet) {
        Transfer transfer = new Transfer();
        transfer.setAccountId(transferRowSet.getInt("account_id"));
        transfer.setDate(transferRowSet.getDate("date").toLocalDate());
        transfer.setFromUser(transferRowSet.getInt("from_username"));
        transfer.setToUser(transferRowSet.getInt("to_username"));
        transfer.setId(transferRowSet.getInt("id"));
        transfer.setTransferAmount(transferRowSet.getBigDecimal("amount"));
        transfer.setStatus(transferRowSet.getString("status"));
        return transfer;
    }

}
