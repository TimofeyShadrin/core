package ru.gb.homework.five.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Backup {
    public static void reserve (String backupDirectoryPath) {

        File directory = new File(".");

        // Создаем папку для резервной копии, если ее нет
        File backupDirectory = new File(backupDirectoryPath);

        if (!backupDirectory.exists()) {
            backupDirectory.mkdirs();
        }
        // Создаем массив всех файлов в текущей директории
        File[] files = directory.listFiles();

        // Копируем каждый файл в папку с резервной копией
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                try (FileInputStream inputStream = new FileInputStream(file);
                     FileOutputStream outputStream = new FileOutputStream(
                             backupDirectoryPath + "/" + file.getName())) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Создана резервная копия файла " + file.getName());
                } catch (IOException e) {
                    System.out.println("Ошибка при создании резервной копии файла " + file.getName());
                }
            }
        }

        // Сообщение о завершении работы
        System.out.println("Резервное копирование завершено");
    }
}
