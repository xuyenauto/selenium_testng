package page_objects.inventory;

import helpers.CommonActions;
import helpers.CommonFunctions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;

public class InventoryProductsUpdateQuantityPage extends GenericPage {

    public InventoryProductsUpdateQuantityPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[normalize-space()=\"Create\"]")
    private WebElement createButton;

    @FindBy(css = "div[name='location_id'] input[type='text']")
    private WebElement locationInput;

    @FindBy(css = "div[name='package_id'] input[type='text']")
    private WebElement packageDropdown;

    @FindBy(css = "div[name='package_id'] input[type='text']")
    private WebElement packageInput;

    @FindBy(css = "input[name=\"inventory_quantity\"]")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[normalize-space()=\"Save\"]")
    private WebElement saveButton;

    //    ===================================New Package Form===============================
    @FindBy(xpath = "//div[@class=\"modal-content\"]//span[.=\"Create\"]")
    private WebElement saveNewPackageButton;

    @Override
    protected void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver, createButton);
    }

    public void selectLocation(String location) {
        CommonActions.setText(driver, locationInput,location);
        CommonFunctions.sleep(1);
        CommonActions.pressKey(locationInput,"Enter");
        if(CommonActions.isExistElementBy(driver, By.xpath("//div[@class=\"modal-content\"]//span[.=\"Create\"]"))){
            CommonActions.click(driver, saveNewPackageButton);
            CommonFunctions.sleep(1);
        }
    }

    /**
     * This function should refactor after understanding more business
     * @param value
     */
    public void selectPackage(String value) {
        CommonActions.click(driver, packageInput);
        CommonActions.setText(driver, packageInput, value);
        CommonFunctions.sleep(1);
        String xpath = "//a[contains(.,'Create and Edit...')]";
        WaitUtils.waitForElementClickable(driver,driver.findElement(By.xpath(xpath)));
        CommonActions.click(driver, driver.findElement(By.xpath(xpath)));
        CommonFunctions.sleep(1);
        if(CommonActions.isExistElementBy(driver, By.xpath("//div[@class=\"modal-content\"]//span[.=\"Create\"]"))){
            CommonActions.click(driver, saveNewPackageButton);
            CommonFunctions.sleep(1);
        }else if(CommonActions.isExistElementBy(driver, By.xpath("//div[@class=\"modal-content\"]//span[.=\"Save\"]"))){
            CommonActions.click(driver, driver.findElement(By.xpath("//div[@class=\"modal-content\"]//span[.=\"Save\"]")));
            CommonFunctions.sleep(2);
        }else{
            CommonActions.pressKey(packageInput,"Enter");
        }

    }

    public InventoryPage updateQuantity(String location, String packageValue, String quantity){
        CommonActions.click(driver, createButton);
        WaitUtils.waitForElementClickable(driver,saveButton);
        CommonFunctions.sleep(1);
        selectLocation(location);
        CommonFunctions.sleep(1);
        selectPackage(packageValue);
        CommonActions.setText(driver, quantityInput, quantity);
        clickOnSaveButton();
        return new InventoryPage();
    }

    public void clickOnSaveButton() {
        CommonActions.click(driver, saveButton);
    }

    public void clickOnCreateNewPackageButton() {
        CommonActions.click(driver, saveNewPackageButton);
    }


}
