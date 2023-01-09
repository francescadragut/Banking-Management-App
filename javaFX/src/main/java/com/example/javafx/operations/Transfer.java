//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.operations;

import com.example.javafx.account.Account;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;

public interface Transfer {
    void transfer(Account account, double amount) throws DeposeException, RetrieveException, BlockedException;
}
