package ru.gb.homework.four;

import ru.gb.homework.four.exception.MyArrayDataException;
import ru.gb.homework.four.exception.MyArraySizeException;
import ru.gb.homework.four.exception.base.MyException;

import java.util.logging.Logger;

public class App 
{
    private final static Logger LOGGER = Logger.getLogger(App.class.getSimpleName());

    public static void main( String[] args ) {

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = sumArrayElements(array);
            LOGGER.info("Сумма элементов массива: " + result);
        } catch (MyException e) {
            LOGGER.warning(e.getLocalizedMessage());
        }
    }

    public static int sumArrayElements(String[][] array) throws MyException {
        int size = 4;
        int sum = 0;

        if (array.length != size || array[0].length != size) {
            throw new MyArraySizeException("Неверный размер массива");
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке ", i, j);
                }
            }
        }
        return sum;
    }
}
