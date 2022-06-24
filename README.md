# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservationModel for the movie
  * And, system can calculate the ticket fee for customer's reservationModel
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format

## Design Decisions
* For standardized and cleaner interaction with this application, I have implemented it as an API with multiple endpoints
* I have added request validation to ensure proper usage of the application (see user guide below)
* Some variable names have been changed for clarity

# User Guide 
## Usage 
1) The user should first call the get-movie-list API to find which movie he or she would like to create a reservation for.
2) The user should then call the create-reservation API to create a reservation and determine the total price of the reservation.

## get-movie-list API 
* This is a GET method hosted at http://localhost:8080/v1/movie-theater/get-movie-list

## create-reservation API 
* This is a POST method hosted at http://localhost:8080/v1/movie-theater/create-reservation
* Request Body Example:
{
  "name": "Bayard Eton",
  "movieId": 3,
  "numberOfTickets": 2
}
* Request Body Schema: (name: String, any length) (movieId: Integer, x > 0) (numberOfTickets: Integer, x > 0)
* Note: **ALL FIELDS ARE REQUIRED IN THE REQUEST** 

## Error Codes For create-reservation:
* NUM001: This error code will appear if one of the integer fields is not passed, or if it is passed as a negative number or 0 
* CUS001: This error code will appear if the name is not passed
