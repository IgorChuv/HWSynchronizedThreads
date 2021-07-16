import java.util.*;

public class Dealer {
    private final String dealerName;
    private final int timeToBuy = 2000;
    private final int timeToReceive = 3000;
    public final int maxDailyClientCount;

    final List<Car> carAvailable = new ArrayList<>();

    public Dealer(String dealerName, int IndividualMaxDailyClientCount) {
        this.dealerName = dealerName;
        this.maxDailyClientCount = IndividualMaxDailyClientCount;
    }

    public void sellCar() {
        try {
            //Начало блока синхронизации
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " пришёл в автосалон " + dealerName);
                while (carAvailable.isEmpty()) {
                    System.out.println("Для " + Thread.currentThread().getName() + " машин " + dealerName + " нет!");
                    wait();
                }
            }
            //Конец блока синхронизации
            Thread.sleep(timeToBuy);
            carAvailable.remove(0);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком автомобиле " + dealerName);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public int getMaxDailyClientCount() {
        return maxDailyClientCount;
    }

    public void carReceived() {

        synchronized (this) {
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
}

