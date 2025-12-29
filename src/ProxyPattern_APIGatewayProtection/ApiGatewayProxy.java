package ProxyPattern_APIGatewayProtection;

// 3. The Proxy (The Gatekeeper)
public class ApiGatewayProxy implements DataService {
    private RealCompanyDataService realService;
    private String userRole;
    private int requestCount = 0;

    public ApiGatewayProxy(String userRole) {
        this.userRole = userRole;
        this.realService = new RealCompanyDataService();
    }

    @Override
    public String getFinancials(String companyId) {
        // Check 1: Security (RBAC)
        if (!"PREMIUM_USER".equals(userRole) && !"ADMIN".equals(userRole)) {
            throw new SecurityException("403 Forbidden: Upgrade to Premium to view Financials.");
        }

        // Check 2: Rate Limiting
        if (requestCount >= 3) {
            throw new RuntimeException("429 Too Many Requests: Slow down!");
        }

        // Logging
        System.out.println("[PROXY] Request authorized. Forwarding to Real Service...");
        requestCount++;

        // Delegation: Call the real object
        return realService.getFinancials(companyId);
    }
}
