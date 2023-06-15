package ru.gb.homework.four.exception.base;

public abstract class MyException extends IllegalStateException{

    public MyException(String s) {
        super(s);
    }
}
