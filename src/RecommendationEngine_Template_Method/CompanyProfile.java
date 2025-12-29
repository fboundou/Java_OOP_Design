package RecommendationEngine_Template_Method;

// The Domain Object
public class CompanyProfile {
    String id;
    String description; // Text description
    double revenue;
    double[] featureVector; // Normalized vector (e.g., [Technology, SaaS, 10M-50M, ...])

    public CompanyProfile(String id, double[] features) {
        this.id = id;
        this.featureVector = features;
    }
}
