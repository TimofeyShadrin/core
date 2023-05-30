package ru.gb.core.homework.two;

import java.util.Random;
import java.util.Scanner;

public class App
{
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '•';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля
    private static final Random random = new Random();
    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля

    public static void main(String[] args) {
        initialize();
        printField();
    }

    /**
     * Инициализация игрового поля
     */
    private static void initialize(){
        // Установим размерность игрового поля
        fieldSizeX = 5; // количество столбцов
        fieldSizeY = 3; // количество строк

        field = new char[fieldSizeY][fieldSizeX];
        // Пройдем по всем элементам массива
        for (int x = 0; x < fieldSizeY; x++) {
            for (int y = 0; y < fieldSizeX; y++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     */
    private static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++){
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++){
            System.out.print(i + 1 + "|");

            for (int j = 0; j <  fieldSizeX; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++){
            System.out.print("-");
        }
        System.out.println();
    }

}
