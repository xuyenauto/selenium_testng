package testcases;

import helpers.ReadDataFile;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.HomePage;
import page_objects.inventory.InventoryPage;
import page_objects.LoginPage;
import page_objects.inventory.InventoryMenuPage;
import page_objects.inventory.InventoryProductsCreationPage;
import page_objects.inventory.InventoryProductsUpdateQuantityPage;
import page_objects.manufacturing.ManufacturingOrdersCreationPage;
import page_objects.manufacturing.ManufacturingOrdersPage;
import page_objects.manufacturing.ManufacturingPage;

import static extent_report.ExtentTestManager.startTest;

public class ManufacturingTests extends TestBase {


    @Test(priority = 1, description = "E2E test case for Manufacturing Orders")
    public void verifyTheManufacturingOrderIsCreated() {

        startTest("ManufacturingTests", "E2E test case for Manufacturing Orders");


        //Test data
        ReadDataFile readDataFile = new ReadDataFile();
        String username = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "username");
        String password = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "password");
        String inventory_feature = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "inventory_feature");
        String product = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "products");
        String location = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "location");
        String packageValue = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "package");
        String quantity = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "quantity");
        String manufacturing_feature = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "manufacturing_feature");
        String consume = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "consume");
        String status = readDataFile.readJsonFile("/testdata/data.json", "ManufacturingOrder", "status");

        //Pages
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        InventoryPage inventoryPage = new InventoryPage();
        InventoryMenuPage inventoryMenuPage = new InventoryMenuPage();
        InventoryProductsCreationPage inventoryProductsCreationPage = new InventoryProductsCreationPage();
        InventoryProductsUpdateQuantityPage inventoryProductsUpdateQuantityPage = new InventoryProductsUpdateQuantityPage();
        ManufacturingPage manufacturingPage = new ManufacturingPage();
        ManufacturingOrdersCreationPage manufacturingOrdersCreationPage = new ManufacturingOrdersCreationPage();
        ManufacturingOrdersPage manufacturingOrdersPage;

        //Login
        loginPage.login(username, password);
        homePage.waitForPageIsDisplayed();

        //Open Inventory feature
        homePage.openFeature(inventory_feature);
        inventoryPage.waitForPageIsDisplayed();


        //Search and generate product name to have a unique product
        String productName = inventoryPage.searchAndGenerateProductName();

        //Open Products->Product and click create
        inventoryMenuPage.openProducts(product)
                .clickOnCreateButton();
        //Create product
        inventoryProductsUpdateQuantityPage = inventoryProductsCreationPage.enterProductName(productName)
                .clickOnSaveButton()
                .clickOnUpdateQuantityButton();

        //Update product to have more than 10
        inventoryProductsUpdateQuantityPage.updateQuantity(location, packageValue, quantity)
                .clickOnApplicationIcon()
                .waitForPageIsDisplayed();

        homePage.openFeature(manufacturing_feature);
        manufacturingPage.waitForPageIsDisplayed();

        //Create new Manufacturing Orders
        manufacturingOrdersCreationPage = manufacturingPage.clickOnCreateButton();
        manufacturingPage.waitForPageIsDisplayed();

        manufacturingOrdersCreationPage.createManufacturingOrder(productName);

        //Add at least one measure to process the order
        manufacturingOrdersCreationPage.addComponents(productName, consume)
                .clickOnConfirmButton()
                .clickOnMaskAsDoneButton()
                .clickOnApplyButton()
                .clickOnSaveButton();

        //The status should be Done
        Assert.assertEquals(status, manufacturingOrdersCreationPage.getStatus(), "The Order status is wrong");

        //Navigate to Manufacturing Orders page and search
        manufacturingOrdersPage = manufacturingPage.clickOnManufacturingOrdersLink();
        manufacturingOrdersPage.waitForPageIsDisplayed();
        manufacturingOrdersPage.removeFilter()
                .filterWithStatus("Done")
                .search(productName);

        //Validation the Manufacturing Orders is created with correct product and status
        Assert.assertTrue(manufacturingOrdersPage.isExistProduct(productName),"Unable to find the product in the Manufacturing Order page");
        Assert.assertEquals(manufacturingOrdersPage.getState(),"Done","The state at the Manufacturing Order page is wrong");

    }

}
