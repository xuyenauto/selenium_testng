package page_objects.manufacturing;

import helpers.CommonActions;
import helpers.CommonFunctions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;
import page_objects.HomePage;

public class ManufacturingPage extends GenericPage {

    public ManufacturingPage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[text()='Manufacturing']")
    private WebElement title;

    @FindBy(css = "input[role=\"searchbox\"]")
    private WebElement searchBox;

    @FindBy(xpath = "//button[normalize-space()=\"Create\" and @type=\"button\"]")
    private WebElement createButton;

    @FindBy(xpath = "//a[normalize-space()=\"Manufacturing Orders\" and @href=\"#\"] ")
    private WebElement manufacturingOrdersLink;

    @Override
    public void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver, title);
    }

    public ManufacturingOrdersCreationPage clickOnCreateButton(){
        CommonActions.click(driver, createButton);
        return  new ManufacturingOrdersCreationPage();
    }

    public ManufacturingOrdersPage clickOnManufacturingOrdersLink(){
        CommonActions.click(driver, manufacturingOrdersLink);
        return new ManufacturingOrdersPage();
    }

}
