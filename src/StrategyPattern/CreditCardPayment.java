package StrategyPattern;

// Concrete Strategy 1: Credit Card
public class CreditCardPayment implements PaymentStrategy {
    private String name;
    private String cardNumber;

    public CreditCardPayment(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending in " + 
            cardNumber.substring(cardNumber.length() - 4));
    }
}
