package ru.gb.homework.three;

public class HourlyWorker extends Worker{
    private final double hourlyRate;

    public HourlyWorker(String name, double hourlyRate) {
        super(name, 0);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateAverageMonthlySalary() {
        return 20.8 * 8 * hourlyRate;
    }
}
