package page_objects.inventory;

import helpers.CommonActions;
import helpers.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;

public class InventoryProductsPage extends GenericPage {

    public InventoryProductsPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[normalize-space()=\"Create\" and @type=\"button\"]")
    private WebElement createButton;

    @Override
    protected void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver,createButton);
    }

    public void clickOnCreateButton(){
        CommonActions.click(driver, createButton);
    }
}
