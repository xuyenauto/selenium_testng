package page_objects;

import helpers.CommonActions;
import helpers.Constants;
import helpers.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends GenericPage{


    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#login")
    private WebElement emailInput;


    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit' and text()='Log in']") // it's used as we can verify the login button
    private WebElement loginButton;

    @Override
    public void waitForPageIsDisplayed() {
        WaitUtils.waitForElementClickable(driver,emailInput);
        WaitUtils.waitForElementClickable(driver,passwordInput);
    }

    public void login(String username, String password){
        CommonActions.setText(driver,emailInput,username);
        CommonActions.setText(driver,passwordInput,password);
        CommonActions.click(driver,loginButton);
    }


}
