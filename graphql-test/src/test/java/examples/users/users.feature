Feature: Access Account Holders

  Background:
    * url 'https://localhost:8080/graphql'

  Scenario: access getAccountHolders
  * text query = 
  """
  {
  	getAccountHolders {
  	}
  }
  """ 
    Given request query 
    When method post
    Then status 200