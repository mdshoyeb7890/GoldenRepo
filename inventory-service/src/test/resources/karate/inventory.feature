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

  Scenario: Get inventory by id (create then get)
    Given request
    """
    {
      "productId": "PROD-GET-001",
      "productName": "Get Test Product",
      "quantity": 50,
      "reorderLevel": 5,
      "unitPrice": 19.99
    }
    """
    When method post
    Then status 200
    * def createdId = response.id
    * path apiPath, createdId
    When method get
    Then status 200
    And match response.id == createdId
    And match response.productId == 'PROD-GET-001'
    And match response.productName == 'Get Test Product'
    And match response.quantity == 50

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
    * url baseUrl
    * path 'api', 'v1', 'inventory', '99999'
    When method get
    Then status 404
    And match response.errorCode == 'NOT_FOUND'
    And match response.status == 404
