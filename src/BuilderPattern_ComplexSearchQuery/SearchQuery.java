package BuilderPattern_ComplexSearchQuery;

// The Complex Object
public class SearchQuery {
    // All fields are final (Immutable)
    private final String industry;
    private final String region;
    private final double minRevenue;
    private final double maxRevenue;
    private final boolean includeExits;

    // Private constructor: Forces use of the Builder
    private SearchQuery(Builder builder) {
        this.industry = builder.industry;
        this.region = builder.region;
        this.minRevenue = builder.minRevenue;
        this.maxRevenue = builder.maxRevenue;
        this.includeExits = builder.includeExits;
    }

    @Override
    public String toString() {
        return "Query [Industry=" + industry + ", Region=" + region + 
               ", MinRev=" + minRevenue + ", Exits=" + includeExits + "]";
    }

    // Static Inner Builder Class
    public static class Builder {
        // Optional parameters initialized to defaults
        private String industry = "All";
        private String region = "Global";
        private double minRevenue = 0.0;
        private double maxRevenue = Double.MAX_VALUE;
        private boolean includeExits = false;

        public Builder industry(String val) {
            this.industry = val;
            return this; // Fluent Interface
        }

        public Builder region(String val) {
            this.region = val;
            return this;
        }

        public Builder minRevenue(double val) {
            this.minRevenue = val;
            return this;
        }
        
        public Builder withExits() {
            this.includeExits = true;
            return this;
        }

        public SearchQuery build() {
            // Validation logic can go here
            if (minRevenue > maxRevenue) {
                throw new IllegalStateException("Min Revenue cannot be greater than Max Revenue");
            }
            return new SearchQuery(this);
        }
    }
}

// // Usage
// public class BuilderDemo {
//     public static void main(String[] args) {
//         // Analyst A: Simple search
//         SearchQuery simpleQuery = new SearchQuery.Builder()
//             .industry("FinTech")
//             .build();

//         // Analyst B: Very specific complex search
//         SearchQuery complexQuery = new SearchQuery.Builder()
//             .industry("Biotech")
//             .region("Boston")
//             .minRevenue(5_000_000)
//             .withExits() // boolean flag
//             .build();

//         System.out.println(simpleQuery);
//         System.out.println(complexQuery);
//     }

