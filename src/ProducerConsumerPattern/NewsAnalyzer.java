package ProducerConsumerPattern;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// 3. The Consumer (Simulates NLP Analysis / Database Saving)
public class NewsAnalyzer implements Runnable {
    private final BlockingQueue<NewsArticle> queue;
    private final String consumerName;
    
    // Thread-safe counter to track total throughput
    private static final AtomicInteger totalProcessed = new AtomicInteger(0);

    public NewsAnalyzer(BlockingQueue<NewsArticle> queue, String name) {
        this.queue = queue;
        this.consumerName = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // TAKE: Blocks if the queue is empty (Waiting for work)
                NewsArticle article = queue.take();

                // Check for "Poison Pill" (Shutdown Signal)
                if (article.toString().contains("POISON_PILL")) {
                    System.out.println(consumerName + " received shutdown signal. Exiting.");
                    break; 
                }

                process(article);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void process(NewsArticle article) {
        // Simulate heavy processing (CPU intensive)
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        int count = totalProcessed.incrementAndGet();
        System.out.println("  " + consumerName + " processed: " + article + " (Total: " + count + ")");
    }
}
