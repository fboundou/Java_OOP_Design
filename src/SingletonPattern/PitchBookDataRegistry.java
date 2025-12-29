package SingletonPattern;

import java.util.HashMap;
import java.util.Map;

/*
C. The Singleton Pattern (Central Data Registry)
In a heavy-data application, you might want a single point of access 
to a connection pool or a global configuration cache.
*/

public class PitchBookDataRegistry {
    // 1. Static variable to hold the single instance
    private static PitchBookDataRegistry instance;
    
    // Simulating a database cache
    private Map<String, String> companyDatabase;

    // 2. Private constructor to prevent instantiation
    private PitchBookDataRegistry() {
        companyDatabase = new HashMap<>();
        companyDatabase.put("AAPL", "Apple Inc. - Tech - Market Cap: 3T");
        companyDatabase.put("STARTUP_X", "Startup X - AI - Series A Funding");
    }

    // 3. Public static method to get the instance
    public static synchronized PitchBookDataRegistry getInstance() {
        if (instance == null) {
            instance = new PitchBookDataRegistry();
        }
        return instance;
    }

    public String getCompanyData(String ticker) {
        return companyDatabase.getOrDefault(ticker, "Data not found");
    }
}
