package CoreDomainModels;

public class Subscriber extends User {
    private SubscriptionManager subscription; // Composition

    public Subscriber(String userId, String name) {
        super(userId, name);
        this.subscription = new SubscriptionManager();
    }

    @Override
    public void accessDashboard() {
        System.out.println("Subscriber " + name + " accessing Standard Investment Dashboard.");
    }

    public SubscriptionManager getSubscription() {
        return this.subscription;
    }
}
