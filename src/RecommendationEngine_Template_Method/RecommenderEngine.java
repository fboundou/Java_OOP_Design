package RecommendationEngine_Template_Method;

import java.util.*;
import java.util.stream.Collectors;

/* Design Pattern: The Template Method Pattern
Recommendation engines often require a series of steps:
1. Pre-filtering candidates based on crude criteria (e.g., industry, size).
2. Calculating similarity scores using complex algorithms (e.g., cosine similarity on feature vectors).
3. Sorting and selecting the top N recommendations.

To make this scalable, we use the Template Method Design Pattern. 
This allows us to define the skeleton of the recommendation algorithm in an abstract class, 
but let subclasses decide the specific "scoring logic" 
(e.g., one algorithm might focus on financial metrics, another on text description).
*/

// 1. Abstract Base Class (Template Method Pattern)
abstract class RecommenderEngine {

    // The "Template Method" - final so it cannot be overridden
    // It defines the rigorous steps of the algorithm.
    public final List<CompanyProfile> recommend(CompanyProfile target, List<CompanyProfile> candidates) {
        
        List<CompanyProfile> filtered = preFilter(target, candidates);
        
        Map<CompanyProfile, Double> scores = new HashMap<>();
        for (CompanyProfile candidate : filtered) {
            double score = calculateSimilarity(target, candidate);
            scores.put(candidate, score);
        }

        return sortAndSelectTop(scores, 5);
    }

    // Step 1: Crude filtering (e.g., remove companies in different regions)
    protected abstract List<CompanyProfile> preFilter(CompanyProfile target, List<CompanyProfile> candidates);

    // Step 2: Complex Math (Subclasses implement this)
    protected abstract double calculateSimilarity(CompanyProfile target, CompanyProfile candidate);

    // Step 3: Common logic for sorting
    private List<CompanyProfile> sortAndSelectTop(Map<CompanyProfile, Double> scores, int k) {
        return scores.entrySet().stream()
            .sorted(Map.Entry.<CompanyProfile, Double>comparingByValue().reversed())
            .limit(k)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
}
