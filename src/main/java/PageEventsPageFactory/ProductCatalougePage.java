package PageEventsPageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilsPageFactory.AbstractMethods;

public class ProductCatalougePage {
	WebDriver driver;
	AbstractMethods method = new AbstractMethods();
	public ProductCatalougePage(WebDriver driver)
	{
		this .driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// locators
	
	@FindBy(xpath ="//div[@class='container']/div[2]/div/div/div")
	List<WebElement> itemsList;
	
	By itemName = By.xpath("//h5/b");
	
    By itemButton = By.xpath("//button[2]");
    
    @FindBy(xpath ="//div[@id='toast-container']/div/div[@aria-label='Product Added To Cart']")
    WebElement toaster;
    
    @FindBy(css=".ng-animating")
    WebElement loader;
    
    // page event methods
    public CartPage addItemTocart(String productNeeded)
    {
    WebElement itemNeeded=	itemsList.stream().filter(product-> product.findElement(itemName).
    			getText().equalsIgnoreCase(productNeeded)).
    	findFirst().orElse(null);
    itemNeeded.findElement(itemButton).click();
    method.waitTillVisibilityOfEle(toaster);
    method.waitTillInvisibilityOfEle(loader);
    CartPage cartPage = new CartPage(driver);
    return cartPage;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
