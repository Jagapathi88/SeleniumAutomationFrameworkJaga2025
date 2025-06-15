package PageEventsPageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}

	//locators
	
	@FindBy(css="li:nth-child(4) button")
	WebElement cartButton;
	
	// page event methods
	public MycartPage clickOnCartButton()
	{
		cartButton.click();
		MycartPage mycartPage= new MycartPage(driver);
		return mycartPage;
	}
	
}
