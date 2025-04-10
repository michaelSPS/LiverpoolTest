@SignUp
Feature: TC 02 Verify that the results displayed includes games for playstation 5 and playstation consoles

  Background:
    Given The user goes to the url

  Scenario: TC 2.1 Acceder a la sección de la página para crear una cuenta nueva
    When El usuario hace click en el botón de CrearCuentaNueva
    Then Se muestra el ProfileUpdate
    Then El usuario hace click en el botón de SoyNuevoEnCamperControl
    And El usuario es dirigido a la página de Registro




