//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.account;

import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;
import com.example.javafx.operations.Transfer;

public class AccountRON extends Account implements Transfer, AccountInterface {
    public AccountRON(String accountNumber, double amount) throws DeposeException, BlockedException {
        super(accountNumber, amount);
    }

    public double getInterest() {
        return this.amount < 500.0 ? 0.03 : 0.08;
    }



    public String toString() {
        return "Account RON [" + super.toString() + "]";
    }

    public void transfer(Account c, double s) throws DeposeException, RetrieveException, BlockedException {
        if (c != null) {
            c.retrieve(s);
            this.depose(s);
        }

    }

    public TYPE getType() {
        return TYPE.RON;
    }
}
