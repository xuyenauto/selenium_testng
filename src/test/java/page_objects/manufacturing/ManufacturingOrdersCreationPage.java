package page_objects.manufacturing;

import helpers.CommonActions;
import helpers.CommonFunctions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;
import page_objects.inventory.InventoryProductsUpdateQuantityPage;

public class ManufacturingOrdersCreationPage extends GenericPage {

    public ManufacturingOrdersCreationPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[name=\"product_id\"] input[type=\"text\"]")
    private WebElement productNameInput;

    @FindBy(xpath = "//button[normalize-space()=\"Save\"]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()=\"Confirm\"]")
    private WebElement confirmButton;

    @FindBy(xpath = "//span[normalize-space()=\"Mark as Done\"]")
    private WebElement maskAsDoneButton;

    @FindBy(xpath = "//button[normalize-space()=\"Create\"]")
    private WebElement createButton;

    @FindBy(css = "input[placeholder='Search...']")
    private WebElement searchInput;

    //    ================Add components===========================================
    @FindBy(xpath = "//a[normalize-space()=\"Add a line\"]")
    private WebElement addALineButton;

    @FindBy(xpath = "(//div[@name='product_id']//input[@type='text'])[2]")
    private WebElement productInput;

    @FindBy(css = "input[name='product_uom_qty']")
    private WebElement toConsumeInput;

    @FindBy(css = "div[name=\"product_uom\"]>div")
    private WebElement measureDropdown;

    @FindBy(css = "button[title=\"Current state\"]")
    private WebElement currentStateText;

    //    ======================Immediate Production Dialog=======================
    @FindBy(xpath = "//span[normalize-space()=\"Apply\"]")
    private WebElement applyButton;

    @FindBy(xpath = "//span[normalize-space()=\"cancel\"]")
    private WebElement cancelButton;

    @Override
    public void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver, productNameInput);
    }

    public ManufacturingOrdersCreationPage enterProductName(String name) {
        CommonActions.setText(driver, productNameInput, name);
        return this;
    }

    public ManufacturingOrdersCreationPage clickOnSaveButton() {
        CommonActions.click(driver, saveButton);
        WaitUtils.waitForElementDisable(driver,saveButton);
        return this;
    }

    public ManufacturingOrdersCreationPage createManufacturingOrder(String productName) {
        CommonActions.click(driver, productNameInput);
        String xpath = "//a[text()='Search More...']";
        if (CommonActions.isExistElementBy(driver, By.xpath(xpath))) {
            driver.findElement(By.xpath(xpath)).click();
            CommonActions.setText(driver, searchInput, productName);
            CommonActions.pressKey(searchInput, "Enter");
            CommonActions.click(driver, driver.findElement(By.cssSelector("td[title='" + productName + "']")));
            CommonFunctions.sleep(1);
        } else {
            enterProductName(productName);
            CommonFunctions.sleep(2);
            CommonActions.pressKey(productNameInput, "Enter");
            CommonFunctions.sleep(1);
        }

        return this;
    }

    public ManufacturingOrdersCreationPage addComponents(String product, String toConsume) {
        CommonActions.click(driver, addALineButton);
        WaitUtils.waitForElementClickable(driver,productInput);
        CommonFunctions.sleep(1);
        //Add product
        CommonActions.setText(driver, productInput, product);
        CommonFunctions.sleep(1);
        CommonActions.pressKey(productInput, "Enter");

        //Add to consume
        CommonFunctions.sleep(1);
        CommonActions.setText(driver, toConsumeInput, toConsume);
        CommonFunctions.sleep(1);
        CommonActions.pressKey(productInput, "Enter");
        CommonFunctions.sleep(1);

        return this;
    }

    public ManufacturingOrdersCreationPage clickOnConfirmButton() {
        CommonActions.click(driver, confirmButton);
        WaitUtils.waitForElementDisable(driver,confirmButton);
        return this;
    }

    public ManufacturingOrdersCreationPage clickOnMaskAsDoneButton() {
        CommonActions.click(driver, maskAsDoneButton);
        return this;
    }

    public ManufacturingOrdersCreationPage clickOnApplyButton() {
        CommonActions.click(driver, applyButton);
        WaitUtils.waitForElementDisable(driver,applyButton);
        return this;
    }

    public String getStatus() {
        return CommonActions.getText(driver, currentStateText);
    }


}
