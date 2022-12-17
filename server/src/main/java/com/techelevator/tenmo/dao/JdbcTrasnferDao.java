package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTrasnferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTrasnferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transfer> getAllTransfer(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while(result.next()) {
            Transfer transfer = mapToRowSet(result);
            transfers.add(transfer);
        }
        return transfers;
    }

    //todo: if statement & create a new map with only specific rowset parameters
    @Override

    public Transfer getTransfer(int id) {
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        result.next();
        return mapToRowSet(result);
    }

    @Override
    public Transfer createTransfer(Transfer createTransfer) {
        String sql = "INSERT INTO transfer (from_username, to_username, transfer_amount, date_and_time, status) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id";
        LocalDateTime currentDateTime = LocalDateTime.now();
        String status = "Approved";
        Integer transferId = jdbcTemplate.queryForObject(sql, Integer.class,
                createTransfer.getFromUsername(),
                createTransfer.getToUsername(),
                createTransfer.getTransferAmount(),
                currentDateTime,
                status);
        return getTransfer(transferId);

    }

    @Override
    public Transfer sendTransfer(Transfer newTransfer) {
        String sql = "INSERT INTO transfer (from_username, to_username, transfer_amount, date_and_time, status) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id";
        LocalDateTime currentDateTime = LocalDateTime.now();
        String status = "Approved";
        Integer transferId = jdbcTemplate.queryForObject(sql, Integer.class,
                newTransfer.getFromUsername(),
                newTransfer.getToUsername(),
                newTransfer.getTransferAmount(),
                currentDateTime,
                status);
        newTransfer = getTransfer(transferId);
        String sqlUpdate = "UPDATE account SET balance = balance + ? WHERE user_id IN (SELECT user_id FROM tenmo_user WHERE username = ?)";
        jdbcTemplate.update(sqlUpdate, newTransfer.getTransferAmount(), newTransfer.getFromUsername());
        String sqlUpdateSubtract = "UPDATE account SET balance = balance - ? WHERE user_id IN (SELECT user_id FROM tenmo_user WHERE username = ?)";
        jdbcTemplate.update(sqlUpdateSubtract, newTransfer.getTransferAmount(), newTransfer.getFromUsername());
        return newTransfer;
    }



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
        transfer.setDate(transferRowSet.getDate("date_and_time").toLocalDate());
        transfer.setFromUsername(transferRowSet.getString("from_username"));
        transfer.setToUsername(transferRowSet.getString("to_username"));
        transfer.setTransferAmount(transferRowSet.getBigDecimal("transfer_amount"));
        transfer.setStatus(transferRowSet.getString("status"));
        return transfer;
    }
}
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
