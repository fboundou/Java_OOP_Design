package FactoryPattern;

// Concrete Product B
class DueDiligenceReport implements Report {
    @Override
    public void generate(String dataContext) {
        System.out.println("Generating Deep-Dive Due Diligence for: " + dataContext);
        System.out.println("...Adding Financial Risk Assessment...");
    }
}
