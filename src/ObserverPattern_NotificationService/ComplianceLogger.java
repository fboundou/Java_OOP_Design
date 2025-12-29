package ObserverPattern_NotificationService;

// 4. Concrete Observer B: Audit Logging
public class ComplianceLogger implements DealObserver {
    @Override
    public void onNewDeal(DealEvent deal) {
        System.out.println("[AUDIT LOG] Recording transaction " + deal.dealId + " for SEC compliance.");
    }
}
