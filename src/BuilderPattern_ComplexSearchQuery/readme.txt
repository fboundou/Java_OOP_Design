2. Builder Pattern (Complex Search Query)
Context: An analyst might search for "SaaS companies", 
OR "SaaS companies in Seattle with >$10M revenue". 
A constructor like new SearchQuery("SaaS", "Seattle", 10000000) is confusing and error-prone 
(telescoping constructor antipattern). 
The Builder makes this readable and flexible.