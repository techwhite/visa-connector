
# Assumption
1. Our service highly dependents on visa service for subsequent processing.
2. We need to build a project to check visa service's availability frequently
3. This project also need to provide restful api for user to manually check
4. Currently only in local dev environment and might deploy to production in the future

# Restful API Document
Please reference the document: API Document.md in root directory of the project

# How to Run this project
1. Execute "maven clean -U package" to build final package into target directory
2. Execute "java -jar target/visa-connector-0.0.1-SNAPSHOT.jar" to run the project
# How to debug this project
1. Add Run/Debug configuration in Intelli idea
2. Set breakpoint in the code
3. Click debug button in the ide to start the project
4. Send request via Postman and then the breakpoint will be hit

# Send request via Postman
1. First send Post /login request to get token
2. Then send Get /connection request with token in the header which is got in #1 

# Monitor Service performance
http://localhost:8080/actuator

# Further Improvement in implementation
1. Better Document and Comment
2. Save password, secureKey, and keyAndCertificate file in a more secure place
3. Add role based authorization for GET /connection
4. Add RateLimit mechanism to limit the call frequency to visa service
5. Collect performance data (e.g. latency) and display them outside of service
6. Parse logging data to monitor the error or exception rate when service is running 
7. Refine test cases and enrich test coverage
8. Checking if want to deploy into docker environment
9. Checking if want to deploy to cloud environment

