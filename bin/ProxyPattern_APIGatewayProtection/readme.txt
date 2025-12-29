3. Proxy Pattern (API Gateway Protection)
Context: We have a CompanyDataService that returns sensitive financial data. 
We don't want to put security logic (checking API keys) 
or rate-limiting logic inside the business service itself. We wrap it in a Proxy.