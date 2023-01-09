package com.example.javafx.operations;

import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;

public class Invoker {
    CommandInterface depose;
    public Invoker(CommandInterface depose){
        this.depose = depose;
    }

    public void start() throws DeposeException, BlockedException {
        this.depose.command();
    }
}
