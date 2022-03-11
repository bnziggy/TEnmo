package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {

    private Long transferId;

    private int transferTypeId;

    private int transferStatusId;

    private User accountFrom;

    private User accountTo;

    private BigDecimal amount;

    public Transfers(Long transferId, int transferTypeId, int transferStatusId, User accountFrom,
                     User accountTo, BigDecimal amount ){
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Transfers(){

    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public User getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(User accountFrom) {
        this.accountFrom = accountFrom;
    }

    public User getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(User accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" +
                "\n Transfer Details" +
                "\n--------------------------------------------" +
                "\n Id: " + transferId +
                "\n From:'" + accountFrom + '\'' +
                "\n To: " + accountTo +
                "\n Type: " + transferTypeId +
                "\n Status: " + transferStatusId +
                "\n Ammount:" + amount ;
    }


}
