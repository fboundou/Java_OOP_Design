package ProducerConsumerPattern;

// 1. The Data Object (Immutable is best for concurrency)
public class NewsArticle {
    private final String id;
    private final String title;
    private final String source;

    public NewsArticle(String id, String title, String source) {
        this.id = id;
        this.title = title;
        this.source = source;
    }

    @Override
    public String toString() {
        return "[" + source + "] " + title;
    }
}
