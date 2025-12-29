package SagaPattern_Orchestrator;

// 3. The Orchestrator (The "Saga Manager")
public class SubscriptionSagaOrchestrator {
    private final PaymentService paymentService;
    private final SubscriptionService subscriptionService;

    public SubscriptionSagaOrchestrator() {
        this.paymentService = new PaymentService();
        this.subscriptionService = new SubscriptionService();
    }

    // The Logic: Execute Transaction -> Handle Failure -> Compensate
    public void purchaseSubscription(String userId, double amount) {
        System.out.println("\n--- Starting Saga for User: " + userId + " ---");
        
        // Step 1: Payment
        try {
            paymentService.charge(userId, amount);
        } catch (Exception e) {
            System.out.println("❌ Saga Failed at Step 1 (Payment): " + e.getMessage());
            System.out.println("No rollback needed (Money wasn't taken).");
            return;
        }

        // Step 2: Activate Subscription
        try {
            subscriptionService.activate(userId);
            System.out.println("✅ Saga Completed Successfully.");
        } catch (Exception e) {
            System.out.println("❌ Saga Failed at Step 2 (Subscription): " + e.getMessage());
            System.out.println("🔄 Initiating Compensating Transaction (Rollback)...");
            
            // COMPENSATING ACTION: Undo Step 1
            compensatePayment(userId, amount);
        }
    }

    private void compensatePayment(String userId, double amount) {
        // In a real system, you might retry this in a loop or add to a "Dead Letter Queue"
        // if the refund fails, because you cannot leave the user charged but unprovisioned.
        try {
            paymentService.refund(userId, amount);
            System.out.println("✅ Rollback Complete: System is back in consistent state.");
        } catch (Exception e) {
            System.out.println("🚨 CRITICAL ALERT: Refund Failed! Manual intervention required.");
        }
    }
}
