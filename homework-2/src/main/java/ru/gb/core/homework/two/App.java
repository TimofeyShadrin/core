package ru.gb.core.homework.two;

import java.util.LinkedList;
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
    private static LinkedList<int[]> lastHuman;
    private static LinkedList<int[]> lastAI;
    private static final  int WIN_COUNT = 4;

    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(lastHuman.getLast(), "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(lastAI.getLast(), "Компьютер победил!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }
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
        field[x][y] = DOT_AI;
        lastAI.add(new int[]{y, x});
    }

    /**
     * Проверка победы
     * TODO: Переработать метод в домашнем задании
     */
    static boolean checkWin(char c) {
        // Проверка по трем горизонталям
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        // Проверка по диагоналям
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        // Проверка по трем вертикалям
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        return false;
    }

    static boolean checkWin(int x, int y, int winCount) {
        int[] result = new int[8];
        int[][] points = new int[8][2];
        char start = field[y][x];
        for (int i = 1; i < winCount; i++) {
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
        for (int i = 0; i < result.length; i++) {
            if (result[i] == (winCount - 1)) {
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

    /**
     * Метод проверки состояния игры
     */
    static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

    static boolean gameCheck(int[] turn, String str) {
        if (checkWin(turn[1], turn[0], WIN_COUNT)) {
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
