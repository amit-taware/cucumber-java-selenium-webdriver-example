package page0bjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DuckDuckGoPage extends BasePage {

    @FindBy(name="q")
    private WebElement searchTxt;

    @FindBy(id = "search_button_homepage")
    private WebElement searchBtn;

    @FindBy(linkText = "Videos")
    private WebElement videosLink;

    @FindBy(className = "tile--vid")
    private List<WebElement> allVideos;

    public DuckDuckGoPage() {
        PageFactory.initElements(driver, this);
    }

    public void goTo(){
        this.driver.get("https://duckduckgo.com/");
        wait.forLoading(5);
    }

    public void doSearch(String keyword){
        wait.forElementToBeDisplayed(5,this.searchTxt,"Search box");
        this.searchTxt.sendKeys(keyword);
        this.searchBtn.click();
    }

    public void goToVideos(){
        wait.forElementToBeDisplayed(5,this.videosLink,"Video Link");
        this.videosLink.click();
    }

    public int getResult(){
        By by = By.className("tile--vid");
        wait.forNumberOfElementsToBeMoreThan(5,by,"Video Link",0);
        System.out.println("Search Result : " + this.allVideos.size());
        return this.allVideos.size();
    }
}
