import java.util.*;

public class Dealer {
    private final String dealerName;
    private final int timeToBuy = 2000;
    private final int timeToReceive = 3000;
    public final int maxDailyClientCount;

    List<Car> carAvailable = new ArrayList<>();

    public Dealer(String dealerName, int IndividualMaxDailyClientCount) {
        this.dealerName = dealerName;
        this.maxDailyClientCount = IndividualMaxDailyClientCount;
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " пришёл в автосалон " + dealerName);
            while (carAvailable.size() == 0) {
                System.out.println("Машин " + dealerName + " нет!");
                wait();
            }
            Thread.sleep(timeToBuy);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком автомобиле " + dealerName);
            carAvailable.remove(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public int getMaxDailyClientCount() {
        return maxDailyClientCount;
    }

    public synchronized void carReceivement() {
        try {
            System.out.println("Отгрузка автомобилей " + dealerName);
            Thread.sleep(timeToReceive);
            carAvailable.add(new Car());
            System.out.println("Отгрузка автомобилей " + dealerName + " завершена");
            notify();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
