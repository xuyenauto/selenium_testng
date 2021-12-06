package page_objects.inventory;

import helpers.CommonActions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;

public class InventoryMenuPage extends GenericPage {

    public InventoryMenuPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[normalize-space()=\"Products\" and @href=\"#\"]")
    private WebElement products;

    @Override
    public void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver,products);
    }

    public InventoryProductsPage openProducts(String item){
        CommonActions.click(driver,products);
        String xpath = "//a[normalize-space()='"+item+"'and @role=\"menuitem\"]";
        CommonActions.click(driver,driver.findElement(By.xpath(xpath)));
        return new InventoryProductsPage();
    }
}
