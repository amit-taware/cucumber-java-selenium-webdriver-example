package page0bjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.IntStream;

public class SearchResultPage extends BasePage {

    private static final String RESULTS_TITLE_SELECTOR = "a h3";

    @FindBy(css = RESULTS_TITLE_SELECTOR)
    private List<WebElement> results;

    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }

    public void checkExpectedUrlInResults(String expectedTitle, int nbOfResultsToSearch) {
        wait.forPresenceOfElements(5, By.cssSelector(RESULTS_TITLE_SELECTOR), "Result title");
        Integer indexOfLink = IntStream.range(0, Math.min(this.results.size(), nbOfResultsToSearch))
                .filter(index -> this.results.get(index).getText().contains(expectedTitle))
                .findFirst()
                .orElse(-1);
        Assert.assertFalse(indexOfLink.equals(-1), expectedTitle + " wasn't found in the results.");
    }
}

