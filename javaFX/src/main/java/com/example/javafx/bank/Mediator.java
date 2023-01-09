package com.example.javafx.bank;

public class Mediator {

    public String name;
    public String id;
    public Mediator(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getID(){
        return this.id;
    }

    public void setID(String id){
        this.id = id;
    }

    public void writeMessage(String message){
        MediatorPattern.writeMessage(this, message);
    }
}
