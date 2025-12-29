package ObserverPattern_NotificationService;

// 1. The Data Payload
public class DealEvent {
    String dealId;
    String companyName;
    double amount;

    public DealEvent(String dealId, String companyName, double amount) {
        this.dealId = dealId;
        this.companyName = companyName;
        this.amount = amount;
    }
}
