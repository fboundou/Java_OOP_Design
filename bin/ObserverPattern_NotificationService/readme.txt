1. Observer Pattern (Notification Service)
Context: When a new "Hot Deal" arrives (via Kafka), 
multiple systems need to know immediately: the Email Service (to alert users) 
and the Audit Log (for compliance). 
The "Subject" doesn't need to know the details of these services; it just notifies them.