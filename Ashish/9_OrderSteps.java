import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class OrderSteps {
    WebDriver driver;

    @Given("User is on the Orders page")
    public void user_is_on_the_orders_page() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("URL_OF_ORDERS_PAGE"); // Replace with actual URL
        driver.manage().window().maximize();
    }

    @Then("User should see the list of orders")
    public void user_should_see_the_list_of_orders() {
        List<WebElement> orders = driver.findElements(By.xpath("//table/tbody/tr"));
        Assert.assertTrue("Order list is empty!", orders.size() > 0);
    }

    @Then("User verifies the first order details")
    public void user_verifies_the_first_order_details() {
        WebElement firstOrder = driver.findElement(By.xpath("//table/tbody/tr[1]"));
        String orderId = firstOrder.findElement(By.xpath("th")).getText();
        String productName = firstOrder.findElement(By.xpath("td[2]")).getText();
        String price = firstOrder.findElement(By.xpath("td[3]")).getText();
        
        System.out.println("Order ID: " + orderId);
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);

        Assert.assertFalse("Order ID is missing!", orderId.isEmpty());
        Assert.assertEquals("ADIDAS ORIGINAL", productName);
        Assert.assertEquals("$ 31500", price);
    }

    @When("User clicks on View button for first order")
    public void user_clicks_on_view_button_for_first_order() {
        WebElement viewButton = driver.findElement(By.xpath("//table/tbody/tr[1]/td[5]/button"));
        viewButton.click();
    }

    @When("User clicks on Delete button for first order")
    public void user_clicks_on_delete_button_for_first_order() {
        WebElement deleteButton = driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]/button"));
        deleteButton.click();
    }

    @Then("The order should be removed from the list")
    public void the_order_should_be_removed_from_the_list() {
        List<WebElement> orders = driver.findElements(By.xpath("//table/tbody/tr"));
        int orderCountAfterDeletion = orders.size();
        Assert.assertTrue("Order was not deleted!", orderCountAfterDeletion < 2);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}