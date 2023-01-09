//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.account;

import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;

public class AccountEUR extends Account implements AccountInterface {
    public AccountEUR(String accountNumber, double amount) throws DeposeException, BlockedException {
        super(accountNumber, amount);
    }

    public double getInterest() {
        return 0.01;
    }


    public String toString() {
        return "Account EUR [" + super.toString() + "]";
    }

    public TYPE getType() {
        return TYPE.EUR;
    }
}
