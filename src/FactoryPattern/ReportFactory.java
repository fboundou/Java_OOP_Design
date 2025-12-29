package FactoryPattern;

// Factory Class
public class ReportFactory {
    public static Report createReport(String reportType) {
        if (reportType.equalsIgnoreCase("MARKET")) {
            return new MarketOverviewReport();
        } else if (reportType.equalsIgnoreCase("DUE_DILIGENCE")) {
            return new DueDiligenceReport();
        }
        throw new IllegalArgumentException("Unknown Report Type");
    }
}
