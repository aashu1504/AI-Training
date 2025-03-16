import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class OrderTableHelper {
    
    public static String getOrderName(WebDriver driver, String orderId) {
        // Locate the table
        WebElement table = driver.findElement(By.cssSelector("table.table"));
        
        // Get all rows in the table body
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        
        // Iterate through the rows to find the matching order ID
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("th"));
            
            if (!cells.isEmpty()) {
                String currentOrderId = cells.get(0).getText().trim();
                
                if (currentOrderId.equals(orderId)) {
                    // Fetch the corresponding Name column (third column)
                    WebElement nameCell = row.findElements(By.tagName("td")).get(1);
                    return nameCell.getText().trim();
                }
            }
        }
        
        return "Order ID not found";
    }
}
