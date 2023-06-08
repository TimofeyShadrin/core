package ru.gb.homework.three;

public class SalariedWorker extends Worker{
    public SalariedWorker(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateAverageMonthlySalary() {
        return salary;
    }
}
