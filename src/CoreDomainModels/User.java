package CoreDomainModels;

/* D. Core Domain Models (Polymorphism & Encapsulation) */

// Abstract Base Class - Inheritance
public abstract class User {
    protected String userId;
    protected String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    // Abstract method forcing subclasses to implement their own view permissions
    public abstract void accessDashboard();
}
