package ru.gb.homework.four.exception;

import ru.gb.homework.four.exception.base.MyException;

public class MyArrayDataException extends MyException {
    private final int row;
    private final int column;
    public MyArrayDataException(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
