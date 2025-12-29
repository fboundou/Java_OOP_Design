//import java.util.concurrent.Flow.Subscriber;

import FactoryPattern.Report;
import FactoryPattern.ReportFactory;
import ObserverPattern_NotificationService.ComplianceLogger;
import ObserverPattern_NotificationService.DealFeedSubject;
import ObserverPattern_NotificationService.EmailNotificationService;
import ProducerConsumerPattern.NewsAnalyzer;
import ProducerConsumerPattern.NewsArticle;
import ProducerConsumerPattern.NewsScraper;
import ProxyPattern_APIGatewayProtection.ApiGatewayProxy;
import ProxyPattern_APIGatewayProtection.DataService;
import SagaPattern_Orchestrator.SubscriptionSagaOrchestrator;
import StrategyPattern.CreditCardPayment;
import StrategyPattern.PayPalPayment;
import SingletonPattern.PitchBookDataRegistry;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import BuilderPattern_ComplexSearchQuery.SearchQuery;
import CoreDomainModels.Subscriber;
//import CoreDomainModels.SubscriptionManager;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== 1. System Initialization (Singleton) ===");
        PitchBookDataRegistry dataSystem = PitchBookDataRegistry.getInstance();
        System.out.println("Data fetched: " + dataSystem.getCompanyData("STARTUP_X"));

        System.out.println("\n=== 2. User & Subscription (Strategy Pattern) ===");
        Subscriber user = new Subscriber("sub-001", "John Doe");
        
        // User chooses to pay via PayPal
        user.getSubscription().setPaymentStrategy(new PayPalPayment("john.doe@techmail.com"));
        user.getSubscription().processRenewal();

        // Later, user switches to Corporate Credit Card
        System.out.println("--- User switches payment method ---");
        user.getSubscription().setPaymentStrategy(new CreditCardPayment("John Doe", "4111-2222-3333-9999"));
        user.getSubscription().processRenewal();

        System.out.println("\n=== 3. Report Generation (Factory Pattern) ===");
        // The client code doesn't need to know the logic of creating the report
        Report report1 = ReportFactory.createReport("MARKET");
        report1.generate("Q4 Tech Sector");

        Report report2 = ReportFactory.createReport("DUE_DILIGENCE");
        report2.generate("Startup X Series B Round");

        // Saga Pattern demonstration would go here
        // Run the workflow
        // Since 'simulateCrash' is true in SubscriptionService, this will trigger the Rollback.
        System.out.println("\n=== 4. Subscription Purchase (Saga Pattern) ===");
        SubscriptionSagaOrchestrator sagaOrchestrator = new SubscriptionSagaOrchestrator();
        sagaOrchestrator.purchaseSubscription("sub-001", 2000.00);
        //sagaOrchestrator.purchaseSubscription("user-123", 99.00);

        // 4. System Manager (ExecutorService)
        System.out.println("\n=== 5. System Manager (ExecutorService) ===");
        // Bounded Queue: Holds max 10 items. 
        // If Producers are too fast, they will BLOCK until Consumers catch up.
        BlockingQueue<NewsArticle> buffer = new LinkedBlockingQueue<>(10);

        // Thread Pool Management
        // We use a fixed pool: 2 Scrapers (Producers) and 3 Analyzers (Consumers)
        ExecutorService producerPool = Executors.newFixedThreadPool(2);
        ExecutorService consumerPool = Executors.newFixedThreadPool(3);

        System.out.println("=== Starting Ingestion Pipeline ===");

        // Start Consumers first so they are ready
        for (int i = 1; i <= 3; i++) {
            consumerPool.submit(new NewsAnalyzer(buffer, "Consumer-" + i));
        }

        // Start Producers
        producerPool.submit(new NewsScraper(buffer, "Bloomberg"));
        producerPool.submit(new NewsScraper(buffer, "Reuters"));

        // Graceful Shutdown Sequence
        producerPool.shutdown(); // No new producers accepted
        try {
            // Wait for producers to finish fetching
            producerPool.awaitTermination(5, TimeUnit.SECONDS);
            
            // Send "Poison Pills" to shut down consumers safely
            // We need 3 pills because we have 3 consumers
            for (int i = 0; i < 3; i++) {
                buffer.put(new NewsArticle("END", "POISON_PILL", "SYSTEM"));
            }
            
            consumerPool.shutdown();
            consumerPool.awaitTermination(5, TimeUnit.SECONDS);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=== Ingestion Complete ===");

        // Usage of Observer Pattern would go here
        System.out.println("\n=== 6. Deal Notification System (Observer Pattern) ===");
        DealFeedSubject kafkaFeed = new DealFeedSubject();

        // Register services
        kafkaFeed.attach(new EmailNotificationService());
        kafkaFeed.attach(new ComplianceLogger());

        // A deal happens
        kafkaFeed.newDealArrived("TechCorp", 1000000.0);
        kafkaFeed.newDealArrived("SpaceX", 500_000_000);

        // Usage of Builder Pattern would go here
        System.out.println("\n=== 7. Complex Search Query Builder ===");
        // Analyst A: Simple search
        SearchQuery simpleQuery = new SearchQuery.Builder()
            .industry("FinTech")
            .build();

        // Analyst B: Very specific complex search
        SearchQuery complexQuery = new SearchQuery.Builder()
            .industry("Biotech")
            .region("Boston")
            .minRevenue(5_000_000)
            .withExits() // boolean flag
            .build();

        System.out.println(simpleQuery);
        System.out.println(complexQuery);

        // Usage of Proxy Pattern would go here
        System.out.println("\n=== 8. API Gateway Proxy ===");
        // Simulate requests from different user roles
        System.out.println("--- Scenario 1: Free User ---");
        DataService freeUser = new ApiGatewayProxy("FREE_USER");
        try {
            freeUser.getFinancials("AAPL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- Scenario 2: Premium User ---");
        DataService premiumUser = new ApiGatewayProxy("PREMIUM_USER");
        System.out.println(premiumUser.getFinancials("AAPL")); // Success

        System.out.println("\n--- Scenario 3: Rate Limit Attack ---");
        try {
            premiumUser.getFinancials("GOOG");
            premiumUser.getFinancials("MSFT");
            premiumUser.getFinancials("TSLA"); // Should fail here
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

/*
Why this design works for a Senior Role:
Open/Closed Principle (SOLID): The Strategy Pattern allows you to add Google Pay or 
Apple Pay later without touching the SubscriptionManager code. You just create a new class.

Separation of Concerns: The Factory Pattern separates the complex logic 
of assembling data for a report from the user asking for it.

Memory Management: The Singleton ensures we don't open 1000 connections to our data cache, 
which is critical for a high-volume platform like PitchBook.
*/