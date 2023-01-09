package com.example.javafx.client;

import com.example.javafx.account.Account;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;

public interface ClientInterface {
    public void addAccount(Account.TYPE type, String code, double amount) throws DeposeException, BlockedException;

    public Account getAccount(String name);

    public void closeAccount(Account account) throws RetrieveException, BlockedException;
    public String getName();
}
