package Controller;

/*
4. Java Implementation: Controller & Service Layer
Here is how the Controller interacts with the Service layer, 
utilizing the DTO Pattern (Data Transfer Object) to decouple 
the internal database model from the external API.
*/

/* 
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

// 1. Controller Layer - Handles HTTP requests
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    // Constructor Injection (Better for testing than @Autowired)
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyProfile(@PathVariable Long id) {
        CompanyDTO company = companyService.getCompanyDetails(id);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/{id}/financials")
    public ResponseEntity<FinancialSummary> getFinancials(@PathVariable Long id) {
        // Checks subscription tier via AOP or Interceptor before this method runs
        return ResponseEntity.ok(companyService.getFinancialHistory(id));
    }
}

// 2. Service Layer - Business Logic
@Service
public class CompanyService {
    
    private final CompanyRepository companyRepo;
    private final DealRepository dealRepo;

    public CompanyDTO getCompanyDetails(Long id) {
        // A. Fetch from DB
        Company company = companyRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        // B. Convert to DTO (Hiding sensitive internal fields)
        CompanyDTO dto = new CompanyDTO();
        dto.setName(company.getName());
        dto.setIndustry(company.getIndustry());
        
        // C. Calculate derived metrics (Business Logic)
        double totalRaised = dealRepo.calculateTotalRaised(id);
        dto.setTotalFunding(totalRaised);

        return dto;
    }
}
*/