package ObserverPattern_NotificationService;

// 3. Concrete Observer A: Email Alerts
public class EmailNotificationService implements DealObserver {
    @Override
    public void onNewDeal(DealEvent deal) {
        System.out.println("[EMAIL SERVICE] Sending 'Hot Deal' alert for: " + deal.companyName);
    }
}
