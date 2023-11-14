package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace;

public class SearchStepDefinitions {

    @Steps
    public CarsAPI carsAPI;

    @When("he calls endpoint {string}")
    public void heCallsEndpoint(String arg0) {
        SerenityRest.given().get(arg0);

    }

    @Then("he sees the request succeed")
    public void heSeesTheResultsSucceed() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @Then("he sees the request not found for the specified item")
    public void heSeesTheResultsNotFound() {
        restAssuredThat(response -> response.statusCode(404));
    }

    @Then("he sees the error with message {string} for the item {string}")
    public void heSeesTheError(String expectedMessage, String expectedItem) {
        restAssuredThat(response -> response.body("detail.error", equalTo(true)));
        restAssuredThat(response -> response.body("detail.message", equalToIgnoringWhiteSpace(expectedMessage)));
        restAssuredThat(response -> response.body("detail.requested_item", equalToIgnoringWhiteSpace(expectedItem)));
    }

    @Then("the response contains {int} items")
    public void theResponseContainsItems(int expectedItemCount) {
        then().body("$.size()", equalTo(expectedItemCount));
    }


    @Then("he sees the results displayed for {string}")
    public void heSeesTheResultsDisplayed(String item) {
        restAssuredThat(response -> response.body("title", contains(item)));
    }

    @Then("he does not see the results")
    public void he_Does_Not_See_The_Results() {
        restAssuredThat(response -> response.body("error", contains("True")));
    }
}
