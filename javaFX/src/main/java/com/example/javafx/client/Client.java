//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.client;

import com.example.javafx.account.Account;
import com.example.javafx.account.Account.TYPE;
import com.example.javafx.account.AccountEUR;
import com.example.javafx.account.AccountRON;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Client implements ClientInterface {
    private String name;
    private String address;
    private Collection<Account> accounts;
    private Account.TYPE type;
    private String accNumber;
    private double amount;
    private String dob;

    public Client(ClientBuilder builder) throws DeposeException, BlockedException {
        this.name = builder.name;
        this.address = builder.address;
        this.accounts = new ArrayList();
        this.type = builder.type;
        this.accNumber = builder.accNumber;
        this.amount = builder.amount;
        this.dob = builder.dob;
        this.addAccount(this.type, this.accNumber, this.amount);
    }

    public String getClientName() {
        return this.name;
    }

    public String getClientAddress() {
        return this.address;
    }

    public Collection<Account> getClientAccounts() {
        return this.accounts;
    }

    public String getDOB() {
        return this.dob;
    }

    @Override
    public void addAccount(Account.TYPE type, String accountNumber, double amount) throws DeposeException, BlockedException {
        Account c = null;
        if (type == TYPE.EUR) {
            c = new AccountEUR(accountNumber, amount);
        } else if (type == TYPE.RON) {
            c = new AccountRON(accountNumber, amount);
        }

        this.accounts.add(c);
    }

    @Override
    public Account getAccount(String accountCode) {
        Iterator var2 = this.accounts.iterator();

        Account a;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            a = (Account)var2.next();
        } while(a == null || !a.getAccountNumber().equals(accountCode));

        return a;
    }

    @Override
    public void closeAccount(Account account) throws RetrieveException, BlockedException {
        double remainingAmount = account.getTotalAmount();
        account.retrieve(remainingAmount);
        this.accounts.remove(account);
    }

    public void blockAccount(Account account) {
        account.blockAccount();
    }

    public String toString() {
        String var10000 = this.name;
        return "\n\tClient [name=" + var10000 + ", address=" + this.address + ", accounts=" + this.accounts.toString() + "]";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }



    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public TYPE getAccountType() {
        return this.type;
    }

    public void setAccountType(Account.TYPE type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return this.accNumber;
    }

    public void setAccountNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public static class ClientBuilder {
        private String name;
        private String address;
        private Account.TYPE type;
        private String accNumber;
        private double amount;
        private String dob;

        public ClientBuilder(String name, String address, Account.TYPE type, String accNumber, double amount) {
            this.name = name;
            this.address = address;
            this.type = type;
            this.accNumber = accNumber;
            this.amount = amount;
        }

        public ClientBuilder dob(String dob) {
            this.dob = dob;
            return this;
        }

        public Client build() throws DeposeException, BlockedException {
            return new Client(this);
        }
    }
}
