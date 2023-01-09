package com.example.javafx.operations;

import com.example.javafx.account.Account;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;

public class DeposeCommand implements CommandInterface{

    Account account;
    double amount;

    public DeposeCommand(Account account, double amount){
        this.account = account;
        this.amount = amount;
    }

    public void command() throws DeposeException, BlockedException {
        this.account.depose(this.amount);
    }
}
