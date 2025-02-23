# Contreebute tests
Repository containing test for the project made for #BestHackaton2025.

## Test Scenarios
 - Test Donate Functionality
 - Test Filters On Leaderboard
 - Test Login Functionality
 - Test Logout Functionality
 - Test Navbar
 - Test Register Functionality
 
 ## Tech Stack
 - Java
 - JUnit
 - Selenium
 
 ## Prerequisites for testing
 
 - Chromedriver
 - Java and JDK 
 - Dependencies for Selenium and JUnit
 - Local Development Server
 
 ## How to run test
 
 - Clone this project
 - Open it using any IDE that has Java and JDK
 - Go to `pom.xml` file and make sure that maven build is performed so dependencies for Selenium and JUnit are configured
 
 ## Tests
 ### Test Donate Functionality
 This test covers single test case where user performs login into our application and then proceeds to donate money. So after user performs login, he is immediately redirected to `/donate` page. There, he selects where he wants his money to go to, how much money he is willing to donate and proceeds to checkout. He is redirected to `https://checkout.stripe.com/` where he inputs his credit card credentials and donates money. After donation, user is redirected to `/leaderboard` page, where he can see his donation as part of the recent donations.

### Test Filters On Leaderboard
This test covers few test cases. What is tested here? When user visits `/leaderboard` page, he can see the history of all donations. There is a filter functionality where user can choose donations he wants to see, ones where money went for trees planting, ones where money went for rivers clean up, or both. All three of these cases were tested.

### Test Login Functionality
This test scenario has two test cases. One where user enters the right credentials for login and gets to experience application in whole. The other one is where user doesn't enter right credentials and is not permitted to go further. 

### Test Logout Functionality
This test scenario contains single test case where user logs into the systems, and immediately logs out. Everything goes as expected, and logout is asserted at the end by checking whether token exists in localStorage.

### Test Navbar
Simple test scenario where every link from navbar (header) is visited and asserted for correctness. 

### Test Register Functionality
In this test scenario, there are two test cases. One is where data is entered and registration goes as expected. The other one is where user tries to register without entering information and he is prevented from registering on this app and stays on registration page.
