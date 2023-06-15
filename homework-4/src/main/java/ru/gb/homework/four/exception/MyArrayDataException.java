package ru.gb.homework.four.exception;

import ru.gb.homework.four.exception.base.MyException;

public class MyArrayDataException extends MyException {
    private final int row;
    private final int column;
    public MyArrayDataException(String s, int row, int column) {
        super(s);
        this.row = row;
        this.column = column;
    }
    @Override
    public String getLocalizedMessage() {
        return s + row + "x" + column;
    }
}
