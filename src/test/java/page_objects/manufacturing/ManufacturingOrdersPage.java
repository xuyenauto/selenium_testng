package page_objects.manufacturing;

import helpers.CommonActions;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_objects.GenericPage;

public class ManufacturingOrdersPage extends GenericPage
{

    public ManufacturingOrdersPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[text()='Manufacturing Orders']")
    private WebElement title;

    @FindBy(xpath = "//span[text()='Filters']")
    private WebElement filtersButton;

    @FindBy(css = "input[placeholder=\"Search...\"]")
    private WebElement searchInput;

    @FindBy(css = "span[name='state']")
    private WebElement stateText;

    @Override
    public void waitForPageIsDisplayed() {
        WaitUtils.waitForElementPresent(driver,title);
        WaitUtils.waitForElementClickable(driver,searchInput);
    }

    public ManufacturingOrdersPage filterWithStatus(String status){
        CommonActions.click(driver,filtersButton);
        String xpath = "//a[text()='"+status+"'and @class='dropdown-item']";
        driver.findElement(By.xpath(xpath)).click();
        //Close filters
        title.click();
        return this;
    }

    public ManufacturingOrdersPage search(String value){
        CommonActions.setText(driver,searchInput,value);
        driver.findElement(By.xpath("//a[contains(.,'Search Product')]")).click();
        return this;
    }

    public ManufacturingOrdersPage removeFilter(){
        while(CommonActions.isExistElementBy(driver,By.cssSelector("i[title='Remove']"))){
            driver.findElement(By.cssSelector("i[title='Remove']")).click();
        }
        return this;
    }

    public boolean isExistProduct(String name){
        return CommonActions.isExistElementBy(driver,By.cssSelector("td[title='"+name+"']"));
    }

    public String getState(){
        return CommonActions.getText(driver,stateText);
    }



}
