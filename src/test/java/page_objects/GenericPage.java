package page_objects;

import org.openqa.selenium.support.PageFactory;
import testcases.TestBase;

public abstract class GenericPage extends TestBase {

    public GenericPage(){
        PageFactory.initElements(driver,this);
    }

    protected abstract void waitForPageIsDisplayed();
}
