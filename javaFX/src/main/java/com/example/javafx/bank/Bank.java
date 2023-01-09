//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx.bank;

import com.example.javafx.client.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Bank {
    private static final int MAX_CLIENTS_NUMBER = 100;
    private Collection<Client> clients = new ArrayList();
    private int clientsNumber;
    private String bankCode = null;

    public Bank(String bankCode) {
        this.bankCode = bankCode;
    }

    public void addClient(Client c) {
        this.clients.add(c);
    }

    public Client getClient(String name) {
        Iterator var2 = this.clients.iterator();

        Client c;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            c = (Client)var2.next();
        } while(!c.getName().equals(name));

        return c;
    }

    public void removeClient(String name) {
        Iterator var2 = this.clients.iterator();

        while(var2.hasNext()) {
            Client c = (Client)var2.next();
            if (c.getName().equals(name)) {
                this.clients.remove(c);
            }
        }

    }

    public String toString() {
        return null;
    }


}
