package page_objects.inventory;

import helpers.CommonActions;
import helpers.CommonFunctions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;
import page_objects.HomePage;

public class InventoryPage extends GenericPage {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "a[title=\"Applications\"]")
    private WebElement applicationIcon;

    @FindBy(xpath = "//a[text()='Inventory']")
    private WebElement title;

    @FindBy(css = "input[role=\"searchbox\"]")
    private WebElement searchBox;

    @Override
    public void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver, title);
    }

    public boolean isProductExist(String name) {
        CommonActions.setText(driver, searchBox, name);
        CommonActions.pressKey(searchBox, "Enter");
        return CommonActions.isExistElementBy(driver, By.xpath("//span[text()='"+name+"']"));
    }

    public String generateProductName(){
        return  CommonFunctions.getCurrentDate("yyyy-MM-dd HH:mm:ss")+"_automation_test".trim().replace(" ","");
    }

    public String searchAndGenerateProductName(){
        String productName;
        do{
            productName = generateProductName();
        }while (isProductExist(productName)!=true);
        return productName;
    }

    public HomePage clickOnApplicationIcon(){
        CommonActions.click(driver,applicationIcon);
        return new HomePage();
    }
}
