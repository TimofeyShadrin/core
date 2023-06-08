package ru.gb.homework.three;

public class App {
    public static void main(String[] args) {
        Worker[] workers = new Worker[3];
        workers[0] = new HourlyWorker("John Doe", 10.0);
        workers[1] = new SalariedWorker("Jane Smith", 2000.0);
        workers[2] = new SalariedWorker("Jack Black", 3000.0);

        for (Worker worker : workers) {
            System.out.printf("%s's average monthly salary is %.2f%n",
                    worker.name, worker.calculateAverageMonthlySalary());
        }
    }
}
