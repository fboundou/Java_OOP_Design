package ObserverPattern_NotificationService;

// 2. The Observer Interface
public interface DealObserver {
    void onNewDeal(DealEvent deal);
}
