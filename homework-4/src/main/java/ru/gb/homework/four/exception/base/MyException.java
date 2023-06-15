package ru.gb.homework.four.exception.base;

public abstract class MyException extends IllegalStateException{
    protected String s;

    public MyException(String s) {
        super(s);
    }
}
