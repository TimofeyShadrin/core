package ru.gb.homework.four.exception;

import ru.gb.homework.four.exception.base.MyException;

public class MyArraySizeException extends MyException {
    public MyArraySizeException(String s) {
        super(s);
    }
}
