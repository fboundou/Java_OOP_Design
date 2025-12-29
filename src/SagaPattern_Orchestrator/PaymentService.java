package SagaPattern_Orchestrator;

/*
Here is the implementation of an Orchestration-based Saga in Java.

The Concept: Orchestration
In this pattern, a central "Orchestrator" (Manager) tells the participants what to do. 
If any step fails, the Orchestrator is responsible for telling the previous participants to undo (compensate) their work.
Java Implementation
We will simulate three components:

1. Payment Service: Can charge and refund.
2. Subscription Service: Can activate (and simulates a crash).
3. Orchestrator: Coordinates the flow and handles the rollback.
*/

// 1. The Payment Microservice (Participant 1)
public class PaymentService {
    public void charge(String userId, double amount) throws Exception {
        System.out.println("[Payment] Charging credit card for User: " + userId + " Amount: $" + amount);
        // Simulate potential network failure during charge
        if (Math.random() > 0.95) throw new Exception("Payment Gateway Timeout");
        System.out.println("[Payment] Charge Successful.");
    }

    public void refund(String userId, double amount) {
        System.out.println("[Payment] ⚠️ REFUNDING $" + amount + " to User: " + userId);
        System.out.println("[Payment] Refund Successful.");
    }
}
