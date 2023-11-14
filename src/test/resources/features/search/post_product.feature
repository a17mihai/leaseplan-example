Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios

  Scenario:
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/apple"
    Then he sees the request succeed
    Then the response contains 0 items

  Scenario:
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/mango"
    Then he sees the request not found for the specified item
    Then he sees the error with message "Not found" for the item "mango"

  Scenario:
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/pasta"
    Then he sees the request succeed
    Then the response contains 20 items

  Scenario:
    When he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/cola"
    Then he sees the request succeed
    Then the response contains 20 items

