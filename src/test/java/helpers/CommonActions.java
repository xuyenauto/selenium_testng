package helpers;

import org.openqa.selenium.*;


import java.util.Arrays;

public class CommonActions {

    public static void setText(WebDriver driver, WebElement element, String key) {
        WaitUtils.waitForElementPresent(driver, element);
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                element.clear();
                element.sendKeys(key);
            } else {
                Log.info("Unable to set text on field");
            }
        } catch (StaleElementReferenceException e) {
            Log.info("#Unable to set text on field: Element is not attached to the page document " + Arrays.toString(e.getStackTrace()));
        } catch (NoSuchElementException e) {
            Log.info("#Unable to set text on field: Element was not found in DOM " + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            Log.info("Unable to set text on field " + Arrays.toString(e.getStackTrace()));
        }
    }


    public static String getText(WebDriver driver, WebElement element) {
        WaitUtils.waitForElementPresent(driver, element);
        return element.getText();
    }


    public static void click(WebDriver driver, WebElement element) {
        WaitUtils.waitForElementClickable(driver, element);
        scrollToElement(driver, element);
        element.click();
    }

    public static void scrollToElement(WebDriver driver, WebElement el) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", el);
        }
    }

    public static boolean isExistElementBy(WebDriver driver, By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getCurrentOperation() {
        return System.getProperty("os.name");
    }

    public static void pressKey(WebElement element, String key) {
        switch (key) {
            case "Enter":
                element.sendKeys(Keys.RETURN);
                break;
            case "Spacebar":
                element.sendKeys(Keys.SPACE);
                break;
            //to do more later
        }
    }

    public static void selectDataFromDropdownList(WebDriver driver, WebElement element, String value) {
        WaitUtils.waitForElementClickable(driver, element);
        element.click();
        String xpath = "//a[normalize-space()='" + value + "']";
        click(driver, driver.findElement(By.xpath(xpath)));

    }
}
