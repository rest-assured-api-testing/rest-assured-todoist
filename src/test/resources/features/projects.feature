Feature: Requests for projects endpoint
  description

  @CreateProject
  Scenario: Get a Project
    Given I build "GET" request
    When I execute "projects/{projectId}" request
    Then the response status code should be "OK"


  Scenario Outline: Create a Project
    Given I build "POST" request
      | name  | <nameProject>  |
    When I execute "projects" request with body
    Then the response status code should be "OK"

  @DeleteProject
    Examples:
      | nameProject |
      | my list     |