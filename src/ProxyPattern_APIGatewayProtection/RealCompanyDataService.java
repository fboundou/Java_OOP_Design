package ProxyPattern_APIGatewayProtection;

// 2. The Real Service (Pure Business Logic)
public class RealCompanyDataService implements DataService {
    @Override
    public String getFinancials(String companyId) {
        // Expensive DB lookup simulation
        return "{ Company: " + companyId + ", EBITDA: $50M, Cash: $12M }";
    }
}
