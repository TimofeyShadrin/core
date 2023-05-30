package ru.gb.core.homework.two;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AppTest extends TestCase {
    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_EMPTY = '•';
    public void testCheckWin() {
        field = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[i][j] = DOT_EMPTY;
            }
        }
        for (int i = 1; i < 5; i++) {
            field[1][i] = DOT_HUMAN;
        }
        boolean expected = true;
        boolean actual = checkWin(1, 1, 4);
        Assert.assertEquals(expected, actual);
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
                boolean valid = (points[j][1] >= 0 && points[j][1] < 5 && points[j][0] >= 0 && points[j][0] < 5);
                if (valid) {
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
}