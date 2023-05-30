package ru.gb.core.homework.two;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '•';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля
    private static final Random random = new Random();
    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля
    private static final LinkedList<int[]> lastHuman = new LinkedList<>();
    private static final LinkedList<int[]> lastAI = new LinkedList<>();
    private static final  int WIN_COUNT = 4;

    public static void main(String[] args) {
        do {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(Objects.requireNonNull(lastHuman.pollLast()), "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(Objects.requireNonNull(lastAI.pollLast()), "Компьютер победил!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
        } while (SCANNER.next().equalsIgnoreCase("Y"));
    }

    /**
     * Инициализация игрового поля
     */
    private static void initialize() {
        // Установим размерность игрового поля
        fieldSizeX = 5; // количество столбцов
        fieldSizeY = 5; // количество строк

        field = new char[fieldSizeY][fieldSizeX];
        // Пройдем по всем элементам массива
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     */
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");

            for (int j = 0; j < fieldSizeX; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты хода X и Y через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[y][x] = DOT_HUMAN;
        lastHuman.add(new int[]{y, x});
    }

    /**
     * Проверка, ячейка является пустой
     */
    static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива, игрового поля)
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
        lastAI.add(new int[]{y, x});
    }

    static boolean checkWin(int x, int y) {
        int[] result = new int[8];
        int[][] points = new int[8][2];
        for (int i = 1; i < App.WIN_COUNT; i++) {
            points[0] = new int[]{y, x + i};
            points[1] = new int[]{y, x - i};
            points[2] = new int[]{y + i, x};
            points[3] = new int[]{y - i, x};
            points[4] = new int[]{y - i, x - i};
            points[5] = new int[]{y - i, x + i};
            points[6] = new int[]{y + i, x - i};
            points[7] = new int[]{y + i, x + i};
            for (int j = 0; j < points.length; j++) {
                if (isCellValid(points[j][1], points[j][0])) {
                    if (field[y][x] == field
                            [points[j][0]]
                            [points[j][1]]){
                        result[j]++;
                    }
                }
            }
        }
        for (int j : result) {
            if (j == (App.WIN_COUNT - 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка на ничью
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++)
                if (isCellEmpty(x, y)) return false;
        }
        return true;
    }

    static boolean gameCheck(int[] turn, String str) {
        if (checkWin(turn[1], turn[0])) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }
}
