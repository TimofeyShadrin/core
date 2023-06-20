package ru.gb.homework.five;

import ru.gb.homework.five.utils.Backup;
import ru.gb.homework.five.utils.Tree;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Tree.print(new File("."), "", true);
        Backup.reserve("./backup");
    }
}
