package com.example.javafx.client;
import com.example.javafx.account.Account;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;

public class ClientDecorator implements ClientInterface {
    ClientInterface clientInterface;

    public ClientDecorator(ClientInterface newClientInterface){
        this.clientInterface = newClientInterface;
    }

    public Account getAccount(String code){
        return this.clientInterface.getAccount(code);
    }

    public void closeAccount(Account account) throws RetrieveException, BlockedException {
        clientInterface.closeAccount(account);
    }

    public void addAccount(Account.TYPE type, String code, double amount) throws DeposeException, BlockedException {
        this.clientInterface.addAccount(type, code, amount);
    }

    public String getName(){
        return this.clientInterface.getName();
    }
}
