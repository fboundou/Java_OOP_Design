package StrategyPattern;

// Concrete Strategy 2: PayPal
public class PayPalPayment implements PaymentStrategy {
    private String emailId;

    public PayPalPayment(String email) {
        this.emailId = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal (" + emailId + ")");
    }
}
