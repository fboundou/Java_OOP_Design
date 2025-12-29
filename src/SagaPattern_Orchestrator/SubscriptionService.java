package SagaPattern_Orchestrator;

// 2. The Subscription Microservice (Participant 2)
public class SubscriptionService {
    public void activate(String userId) throws Exception {
        System.out.println("[Subscription] Attempting to activate Premium Access for: " + userId);
        
        // SIMULATION: This service is "down" or the database is locked
        // Change this boolean to 'false' to test the Happy Path
        boolean simulateCrash = true; 
        
        if (simulateCrash) {
            throw new Exception("Database Connection Failed: Could not update user record.");
        }
        
        System.out.println("[Subscription] Access Activated.");
    }
    
    public void deactivate(String userId) {
        System.out.println("[Subscription] Deactivating access for: " + userId);
    }
}
