package ru.gb.core.homework.one;

import ru.gb.core.homework.one.mypackage.ClassA;
import ru.gb.core.homework.one.mypackage.ClassB;

/**
 * Основной класс Main с методом main, создающий экземпляры ClassA и ClassB и вызывающий их методы
 */
public class App
{
    public static void main( String[] args )
    {
        ClassA a = new ClassA();
        ClassB b = new ClassB();
        a.methodA(); // вызов метода methodA в ClassA
        b.methodB(); // вызов метода methodB в ClassB
    }
}
