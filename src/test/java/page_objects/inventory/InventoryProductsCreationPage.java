package page_objects.inventory;

import helpers.CommonActions;
import helpers.CommonFunctions;
import helpers.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;

public class InventoryProductsCreationPage extends GenericPage {

    public InventoryProductsCreationPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder='Product Name']")
    private WebElement productNameInput;

    @FindBy(xpath = "//button[normalize-space()=\"Save\"]")
    private WebElement saveButton;

    @FindBy(css = "button[name='action_update_quantity_on_hand']>span")
    private WebElement updateQuantity;

    @Override
    protected void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver,productNameInput);
    }

    public InventoryProductsCreationPage enterProductName(String name){
        CommonActions.setText(driver,productNameInput,name);
        return this;
    }

    public InventoryProductsCreationPage clickOnSaveButton(){
        CommonActions.click(driver,saveButton);
        return this;
    }

    public InventoryProductsUpdateQuantityPage clickOnUpdateQuantityButton(){
        CommonFunctions.sleep(5);
        CommonActions.click(driver,updateQuantity);
        return new InventoryProductsUpdateQuantityPage();
    }
}
