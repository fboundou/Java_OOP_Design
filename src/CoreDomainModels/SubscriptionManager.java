package CoreDomainModels;
import StrategyPattern.PaymentStrategy;
// Context Class for Strategy Pattern
public class SubscriptionManager {
    private PaymentStrategy paymentStrategy;
    private double monthlyFee = 2000.00; // Enterprise pricing

    // Setter allows changing strategy at runtime (Polymorphism)
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processRenewal() {
        if (paymentStrategy == null) {
            System.out.println("No payment method selected.");
        } else {
            paymentStrategy.pay(monthlyFee);
            System.out.println("Subscription renewed successfully.");
        }
    }
}
