# RewardsPointsCalculator

This is a Java SpringBoot based API to input customer transactions through a Post call and Get the reward point calculated based on the timeframe along with the total rewards for an individual user.

The Post method is enabled to read the user transaction data through /points end point. Below is the sample curl for the post call.

curl --location --request POST 'localhost:8080/points' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId": "21",
    "payment": {
        "currency": "$",
        "amount": 120.0
    },
    "dateTime": "06-11-2022 02:02:10"
    
The end point /{userId}/startDate/{startDate}/endDate/{endDate} is exposed to Get the Reward points with the individual userid for a particular period of time and it also gives the total rewards for that user. Below is the sample curl for this endpoint.

curl --location --request GET 'localhost:8080/points/2/startDate/01-11-2022 02:02:10/endDate/14-12-2022 02:02:10'

The test cases also calculates the reward points for the user based on the sample dataset that was provided.
