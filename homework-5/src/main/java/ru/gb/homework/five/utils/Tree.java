package ru.gb.homework.five.utils;

import java.io.File;

public class Tree {
    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent); // рисуем отступ
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }

        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        // Получаем количество файлов и директорий в текущей директории
        int totalFilesAndDirs = files.length;
        int subDirTotal = 0;
        int fileTotal = 0;

        // Считаем количество поддиректорий и файлов в текущей директории
        for (File item : files) {
            if (item.isDirectory()) {
                subDirTotal++;
            } else {
                fileTotal++;
            }
        }

        int subDirCounter = 0;
        int fileCounter = 0;

        // Выводим каждую поддиректорию/файл в текущей директории
        for (int i = 0; i < totalFilesAndDirs; i++) {
            File currentFile = files[i];
            if (currentFile.isDirectory()) {
                // Выводим информацию о поддиректории
                if (fileCounter == 0) {
                    indent = indent.substring(0, indent.length() - 2) + "  ";
                }
                subDirCounter++;
                print(currentFile, indent, subDirCounter == subDirTotal && fileCounter == 0);
                subDirCounter--;
            } else {
                // Выводим информацию о файле
                if (subDirCounter == 0 && fileCounter == 0 && i == totalFilesAndDirs - 1) {
                    System.out.print(indent.substring(0, indent.length() - 3));
                    System.out.print("└─");
                } else {
                    System.out.print(indent);
                    System.out.print("├─");
                }

                System.out.println(currentFile.getName());

                fileCounter++;
            }
        }
    }
}
