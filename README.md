Vento Live - Live sentiment analysis from twitter

What do you need:
1. A mongoDB instance running somewhere, for setting up quickly a local database:
    mkdir ~/mongodb
    mongod --dbpath mongodb

(By default mongodb will be found on localhost:27017, se the property file vento-service-rest.properties)

2. A twitter account and the set up for the API, enabling access key and secret key

How to run it (with at least 1Gb heap space -Xmx1024m):

mvn -Xmx1024m jetty-run


Bear in mind that the application will take some time to come up (max 5 minutes).
