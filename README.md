# Vento Live - Live sentiment analysis from twitter

## What do you need:
- A mongoDB instance running somewhere, for setting up quickly a local database:
```
    mkdir ~/mongodb
    mongod --dbpath mongodb
```
(By default mongodb will be found on localhost:27017, se the property file vento-service-rest.properties)

- A twitter account and the set up for the API (https://apps.twitter.com/), enabling access key and secret key. See https://dev.twitter.com/rest/public

## How to run it (with at least 1Gb heap space -Xmx1024m):
Just type:
```
mvn -Xmx1024m jetty-run
```
Connect to http://localhost:8080/vento-live, type in the textbox one or multiple keywords and wait :)


## Note
- Bear in mind that the application will take some time to come up (max 5 minutes).
- The web application doesn't give any feedback and any error :)
