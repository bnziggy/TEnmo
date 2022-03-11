package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTransfersDAO implements TransfersDao {

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcTransfersDAO(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }


    @Override
    public List<Transfers> getAllTransfers() {
        List<Transfers> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transfers transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public Transfers getTransfersByTransferId(long transferId) {
        Transfers transfer = new Transfers();
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        } else {
            System.out.println("Invalid transfer_id");
        }
        return transfer;
    }

    @Override
    public List<Transfers> getTransfersByUserId(int userid) {
        List<Transfers> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfers " +
                "JOIN account ON transfer.account_from = account.account_id " +
                "WHERE account.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userid);
        while (results.next()) {
            Transfers transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public void sendTransfer(Transfers transfers) {
        String sql = "INSERT INTO transfer " +
                "(transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "VALUES (?, ?, ?, ?, ?, ?;";
        jdbcTemplate.update(sql, transfers.getTransferId(), transfers.getTransferTypeId(), transfers.getTransferStatusId(),
                transfers.getAccountFrom(), transfers.getAccountTo(), transfers.getAmount());
    }


    @Override
    public String getTransferTypeDesc(int transferTypeId) {
        String typeDescription = "";
        String sql = "SELECT transfer_type_desc FROM transfer_type WHERE transfer_type_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferTypeId);
        if (results.next()) {
            typeDescription = results.getString("transfer_type_id");
        }
        return typeDescription;
    }

    @Override
    public String getTransferStatusDesc(int statusId) {
        String statusDescription = "";
        String sql = "SELECT transfer_status_desc FROM transfer_status WHERE transfer_status_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, statusId);
        if (results.next()) {
            statusDescription = results.getString("transfer_status_id");

        }
        return statusDescription;
    }


    private Transfers mapRowToTransfer(SqlRowSet rowSet) {
        Transfers transfers = new Transfers();
        transfers.setTransferId(rowSet.getLong("transfer_id"));
        transfers.setTransferStatusId(rowSet.getInt("transfer_type_id"));
        transfers.setTransferStatusId(rowSet.getInt("transfer_status_id"));
        transfers.setAccountFrom(userDao.getUserById(rowSet.getLong("account_from")));
        transfers.setAccountTo(userDao.getUserById(rowSet.getLong("account_to")));
        transfers.setAmount(rowSet.getBigDecimal("amount"));

        return transfers;
    }


}
