//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.test;

import com.example.javafx.account.Account.TYPE;
import com.example.javafx.account.AccountFactory;
import com.example.javafx.account.AccountInterface;
import com.example.javafx.account.AccountRON;
import com.example.javafx.bank.Bank;
import com.example.javafx.bank.Mediator;
import com.example.javafx.client.Client;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;
import com.example.javafx.operations.DeposeCommand;
import com.example.javafx.operations.Invoker;

public class Test {
    public Test() {
    }

    public static void main(String[] args) throws DeposeException, RetrieveException, BlockedException {
        Bank bcr = new Bank("BT bank");

        AccountFactory accountFactory = new AccountFactory();
        AccountInterface accountInterface1 = accountFactory.accountFactory(TYPE.EUR, "EUR873", 300.0);

        System.out.println("" + accountInterface1 + "\n");

        Client client1 = (new Client.ClientBuilder("Popescu Vasile", "Timisoara", TYPE.EUR, "EUR2001", 100.0)).dob("13/06/1970").build();
        bcr.addClient(client1);
        AccountInterface accountInterface2 = accountFactory.accountFactory(TYPE.RON, "EUR124", 400.0);
        client1.addAccount(accountInterface2.getType(), "RON124", 400.0);

        Client client2 = (new Client.ClientBuilder("Panait Mihai", "Bucuresti", TYPE.RON, "RON186", 100.0)).dob("14/01/1950").build();
        bcr.addClient(client2);

        Client cl1 = bcr.getClient("Panait Mihai");
        if (cl1 != null) {
            DeposeCommand deposeCommand = new DeposeCommand(cl1.getAccount("RON186"), 400.0);
            Invoker invoker = new Invoker(deposeCommand);
            invoker.start();
            System.out.println(client1);
        }

        if (client1 != null) {
            client1.getAccount("RON186").retrieve(67.0);
            System.out.println(client1);
        }

        AccountRON account1 = (AccountRON)client1.getAccount("RON186");
        AccountRON account2 = (AccountRON)bcr.getClient("Popescu Vasile").getAccount("RON124");

       // account1.transfer(account2, 40.0);

        Mediator bankMediator = new Mediator("Paul", "K452");
        bankMediator.writeMessage("Hello Client! Bank Mediator is here to help you!");
    }
}
