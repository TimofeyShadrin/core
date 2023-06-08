package ru.gb.homework.three;

public abstract class Worker {
    String name;
    protected double salary;

    public Worker(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    public abstract double calculateAverageMonthlySalary();
}
