package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import page0bjects.DuckDuckGoPage;

public class DuckDuckGoSteps {

    private DuckDuckGoPage duckduckgopage;

    public DuckDuckGoSteps() {
        this.duckduckgopage = new DuckDuckGoPage() ;
    }

    @Given("^I am on the website duck-duck-go$")
    public void iAmOnTheWebsiteDuckDuckGo() {
        this.duckduckgopage.goTo();
    }

    @And("^I enter the \"([^\"]*)\" to search$")
    public void iEnterTheToSearch(String string) {
        this.duckduckgopage.doSearch(string);
    }

    @And("^I navigate to videos search$")
    public void iNavigateToVideosSearch() {
        this.duckduckgopage.goToVideos();
    }

    @Then("^I should get minimum (\\d+) search results$")
    public void iShouldGetMinimumSearchResults(int min) {
        int size = this.duckduckgopage.getResult();
        Assert.assertTrue(size > min);
    }
}
