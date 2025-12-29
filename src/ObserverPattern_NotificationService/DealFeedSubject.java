package ObserverPattern_NotificationService;

import java.util.ArrayList;
import java.util.List;

// 5. The Subject (The "Kafka Listener")
public class DealFeedSubject {
    private List<DealObserver> observers = new ArrayList<>();

    // Subscribe
    public void attach(DealObserver observer) {
        observers.add(observer);
    }

    // Unsubscribe
    public void detach(DealObserver observer) {
        observers.remove(observer);
    }

    // Notify All
    public void notifyObservers(DealEvent deal) {
        for (DealObserver observer : observers) {
            observer.onNewDeal(deal);
        }
    }

    // Simulation of a message arriving from Kafka
    public void newDealArrived(String company, double amount) {
        System.out.println("\n>>> New Message from Kafka Topic: DEALS <<<");
        DealEvent event = new DealEvent("TX-999", company, amount);
        notifyObservers(event);
    }
}
