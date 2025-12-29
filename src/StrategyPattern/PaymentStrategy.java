package StrategyPattern;

/*
A. The Strategy Pattern (Payment Processing)
We use the Strategy pattern to decouple the payment logic from the subscription logic. 
This allows us to add new payment methods (e.g., Crypto, Wire Transfer) 
without modifying the SubscriptionManager class.
*/

// Interface defining the strategy
public interface PaymentStrategy {
    void pay(double amount);
}
