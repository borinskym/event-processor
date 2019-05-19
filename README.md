# event processor

#### in order to start the service please use command 
./gradlew bootRun

#### stats will be available at
http://localhost:8080/v1/stats 


## improvements:

### 1. containarize the application in order to not depend on environment
### 2. if there might be another services that might consume events i would separate the event consumer on a different service and would send message in rabbit/kafka/amazonMQ
### 3. investigate more on reactive libraries and choose the best one   