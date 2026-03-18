@ignore
Feature: Reusable Create Inventory

    Scenario:
        Given url baseUrl
        And path apiPath
        And request
        """
        {
              "productId": "PROD-REUSE-001",
              "productName": "Shared Product",
              "quantity": 10,
              "reorderLevel": 5,
              "unitPrice": 5.0
        }
        """
        When method post
        Then status 200
        #Export th Id so other tests can use it
        * def createdId = response.id
