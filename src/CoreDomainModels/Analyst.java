package CoreDomainModels;

public class Analyst extends User {
    public Analyst(String userId, String name) {
        super(userId, name);
    }

    @Override
    public void accessDashboard() {
        System.out.println("Analyst " + name + " accessing Admin Dashboard with Write Privileges.");
    }
}
