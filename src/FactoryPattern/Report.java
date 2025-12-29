package FactoryPattern;

/*
B. The Factory Pattern (Report Generation)
A PitchBook clone needs to generate various complex reports 
(Market Analysis, Company Profiles, Deal Flow). 
The Factory pattern handles the object creation logic, isolating the client 
from the specific implementation of each report.
*/

// Product Interface
public interface Report {
    void generate(String dataContext);
}
