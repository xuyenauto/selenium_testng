package page_objects;

import helpers.CommonActions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends GenericPage{

    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[text()='Discuss']")
    private WebElement discussFeature;

    @Override
    public void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver,discussFeature);
    }

    public void openFeature(String featureName){
        String xpath = "//div[text()='"+featureName+"']";
        CommonActions.click(driver,driver.findElement(By.xpath(xpath)));
    }
}
