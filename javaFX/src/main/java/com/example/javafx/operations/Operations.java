//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.operations;

import com.example.javafx.account.Account;
import com.example.javafx.account.AccountInterface;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;

public interface Operations {
    double getTotalAmount();

    double getInterest();
    String getAccountNumber();
    void setAccountNumber(String accountNumber);

    Double getAmount();
    void setAmount(Double amount);

    Account.TYPE getType();
    void depose(double amount) throws DeposeException, BlockedException;

    void retrieve(double amount) throws RetrieveException, BlockedException;

    void transfer(AccountInterface account, double amount) throws DeposeException, RetrieveException, BlockedException;

    void blockAccount();

    void unblockAccount();
}
