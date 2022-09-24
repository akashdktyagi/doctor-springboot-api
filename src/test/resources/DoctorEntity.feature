Feature: As a User,
  I am able to Create/Update/Search/Delete New Doctor Entity
  So that new Doctors and their details can be registered in the System

  Scenario: I am able to create new Doctor Entity
    Given I have doctor details as below
    When I create new Doctor using API
    Then new doctor is created

  Scenario: I am able to update an existing Doctor Entity
    Given I have an existing doctor
    When I update a the doctor details
    Then doctor details are updated

  Scenario: I am able to delete an existing Doctor Entity
    Given I have an existing doctor
    When I delete the doctor entity
    Then doctor is deleted

  Scenario: I am able to get all the doctor
    Given I have existing doctors
    When I get the doctors list
    Then all the doctors with details are returned

  Scenario: I am able to get doctor details by name
    Given I have existing doctors
    When I search the doctors list by name
    Then all the doctors with the mentioned name are returned

  Scenario: I am able to get doctor details by id
    Given I have existing doctors
    When I search the doctors list by id
    Then doctors with the mentioned id is returned

  Scenario: I am able to get doctor details by registration id
    Given I have existing doctors
    When I search the doctors list by registrationID
    Then doctors with the right registration id is returned