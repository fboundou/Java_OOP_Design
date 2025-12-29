package ProducerConsumerPattern;

import java.util.concurrent.*;

// 2. The Producer (Simulates fetching data from external APIs)
public class NewsScraper implements Runnable {
    private final BlockingQueue<NewsArticle> queue;
    private final String sourceName;

    public NewsScraper(BlockingQueue<NewsArticle> queue, String sourceName) {
        this.queue = queue;
        this.sourceName = sourceName;
    }

    @Override
    public void run() {
        try {
            // Simulate fetching 5 articles
            for (int i = 1; i <= 5; i++) {
                String title = "Market Update #" + i;
                NewsArticle article = new NewsArticle(sourceName + "-" + i, title, sourceName);
                
                System.out.println("producer-thread -> Fetched: " + article);
                
                // PUT: Blocks if the queue is full (Backpressure)
                queue.put(article); 
                
                // Simulate network latency
                Thread.sleep(200); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted.");
        }
    }
}
