//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.account;

import com.example.javafx.account.Account.TYPE;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;

public class AccountFactory {
    public AccountFactory() {
    }

    public AccountInterface accountFactory(Account.TYPE type, String accountNumber, double amount) throws DeposeException, BlockedException {
        if (type.equals(TYPE.RON)) {
            return new AccountRON(accountNumber, amount);
        } else {
            return type.equals(TYPE.EUR) ? new AccountEUR(accountNumber, amount) : null;
        }
    }
}
