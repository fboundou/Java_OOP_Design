package ProxyPattern_APIGatewayProtection;

// 1. The Interface (Both Real Service and Proxy implement this)
public interface DataService {
    String getFinancials(String companyId);
}
