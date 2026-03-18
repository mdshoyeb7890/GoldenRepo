Feature: Inventory API

  Background:
    * url baseUrl
    * path apiPath
    * header Content-Type = 'application/json'

  Scenario: Create inventory successfully
    Given request
    """
    {
      "productId": "PROD-KARATE-001",
      "productName": "Karate Test Product",
      "quantity": 100,
      "reorderLevel": 10,
      "unitPrice": 29.99
    }
    """
    When method post
    Then status 200
    And match response.id == '#number'
    And match response.productId == 'PROD-KARATE-001'
    And match response.productName == 'Karate Test Product'
    And match response.quantity == 100
    And match response.reorderLevel == 10
    And match response.unitPrice == 29.99
    And match response.createdAt == '#notnull'
    And match response.updatedAt == '#notnull'

  Scenario: Get all inventories
    When method get
    Then status 200
    And match response == '#[]#'

  Scenario: Get inventory by id (create then get) using reusable call
    # call the other feature and capture the result
    * def result = call read('create-inventory.feature')
    * def idToGet = result.createdId

    Given path idToGet
    When method get
    Then status 200
    And match response.id == idToGet
    And match response.productName == 'Shared Product'


  Scenario: Create inventory - validation failure (empty productId)
    Given request
    """
    {
      "productId": "",
      "productName": "Test",
      "quantity": 10,
      "reorderLevel": 5,
      "unitPrice": 9.99
    }
    """
    When method post
    Then status 400
    And match response.errorCode == 'VALIDATION_FAILED'
    And match response.message != null
    And match response.status == 400

  Scenario: Create inventory - validation failure (negative quantity)
    Given request
    """
    {
      "productId": "PROD-002",
      "productName": "Test Product",
      "quantity": -1,
      "reorderLevel": 5,
      "unitPrice": 9.99
    }
    """
    When method post
    Then status 400
    And match response.errorCode == 'VALIDATION_FAILED'
    And match response.status == 400

  Scenario: Get inventory by id - not found
    * path '99999'
    When method get
    Then status 404
    And match response.errorCode == 'NOT_FOUND'
    And match response.status == 404
