package com.example.javafx.client;

public class ClientEconomyAccount extends ClientDecorator{
    public ClientEconomyAccount(ClientInterface newClientInterface){
        super(newClientInterface);
    }

    public String getName(){
        return super.getName() + " - has an economy account.";
    }
}
