package FactoryPattern;

// Concrete Product A
class MarketOverviewReport implements Report {
    @Override
    public void generate(String dataContext) {
        System.out.println("Generating Market Overview for: " + dataContext);
        System.out.println("...Adding Trend Analysis Graphs...");
    }
}
