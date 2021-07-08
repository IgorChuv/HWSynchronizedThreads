public class Main {

    public static void workingDay(Dealer dealer){
        int clientCount = 0;
        while (clientCount < dealer.getMaxDailyClientCount()) {
            clientCount++;
            new Thread(dealer::sellCar, "Покупатель " + clientCount).start();
            new Thread(dealer::carReceivement, "Автовоз").start();
        }
    }

    public static void main(String[] args){
        Dealer dealerToyota = new Dealer("Toyota",10);
        workingDay(dealerToyota);

    }
}
