//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.account;

import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;
import com.example.javafx.operations.Operations;

public abstract class Account implements Operations {
    protected String accountCode = null;
    protected double amount = 0.0;
    protected Boolean isBlocked;

    protected Account(String accountNumber, double amount) throws DeposeException, BlockedException {
        this.isBlocked = Boolean.FALSE;
        this.accountCode = accountNumber;
        this.depose(amount);
    }

    public double getTotalAmount() {
        return this.amount + this.amount * this.getInterest();
    }

    public void blockAccount() {
        this.isBlocked = Boolean.TRUE;
    }
    public void unblockAccount(){
        this.isBlocked = Boolean.FALSE;
    }
    public void depose(double amount) throws DeposeException, BlockedException {
        if (amount < 0.0) {
            throw new DeposeException("Cannot depose a negative amount.");
        } else if (this.isBlocked == Boolean.TRUE) {
            throw new BlockedException("Cannot depose, because the account is blocked.");
        } else {
            this.amount += amount;
        }
    }

    public void retrieve(double amount) throws RetrieveException, BlockedException {
        if (this.amount < 0.0) {
            throw new RetrieveException("Cannot retrieve a negative amount.");
        }else if (amount > this.amount){
            throw new RetrieveException("Cannot retrieve more than the amount in the account.");
        } else if (this.isBlocked == Boolean.TRUE) {
            throw new BlockedException("Cannot retrieve, because the account is blocked.");
        } else {
            this.amount -= amount;
        }
    }

    public void transfer(AccountInterface account, double amount) throws DeposeException, RetrieveException, BlockedException {
        if (account.getType() == this.getType()) {
            this.retrieve(amount);
            account.depose(amount);
        } else if (account.getType() == Account.TYPE.RON && this.getType() == Account.TYPE.EUR) {
            this.retrieve(amount);
            account.depose(amount * 4.8D);
        } else if (account.getType() == Account.TYPE.EUR && this.getType() == Account.TYPE.RON) {
            this.retrieve(amount);
            account.depose(amount / 4.8D);
        }
    }

    public String toString() {
        return "Account: code=" + this.accountCode + ", amount=" + this.amount;
    }

    public String getAccountNumber() {
        return this.accountCode;
    }
    public void setAccountNumber(String accountCode){
        this.accountCode = accountCode;
    }

    public Double getAmount(){
        return this.amount;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public static enum TYPE {
        EUR,
        RON,
        UNDEF;

        private TYPE() {
        }
    }
}
