package ORM_Layer;

import java.util.ArrayList;
import java.util.List;

/*
4. Java Implementation (ORM Layer)
To map this schema to your Java application using JPA/Hibernate annotations:
*/
@Entity
@Table(name = "deals")
public class Deal {
    /* 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // One deal has many participants (investors)
    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL)
    private List<DealParticipation> participants = new ArrayList<>();

    private Double amountRaised;
    
    @Enumerated(EnumType.STRING)
    private DealType type; // SERIES_A, SERIES_B, etc.

    // Helper method to add participant (Encapsulation)
    public void addParticipant(Fund fund, Double amount, boolean isLead) {
        DealParticipation participation = new DealParticipation(this, fund, amount, isLead);
        this.participants.add(participation);
    }*/
}
