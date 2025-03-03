import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderTableHelper {
    
    public static String getOrderName(WebDriver driver, String orderId) {
        try {
            // Use XPath to find the row where the Order ID matches
            WebElement row = driver.findElement(By.xpath("//table[contains(@class, 'table')]/tbody/tr[th[text()='" + orderId + "']]"));
            
            // Locate the Name column in the same row (third column - index 2 in td)
            WebElement nameCell = row.findElements(By.tagName("td")).get(1);
            
            return nameCell.getText().trim();
        } catch (Exception e) {
            return "Order ID not found";
        }
    }
}
