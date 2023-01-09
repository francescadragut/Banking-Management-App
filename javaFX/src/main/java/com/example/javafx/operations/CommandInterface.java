package com.example.javafx.operations;

import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;

public interface CommandInterface {
    void command() throws DeposeException, BlockedException;
}
