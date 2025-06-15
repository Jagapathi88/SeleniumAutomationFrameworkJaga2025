package PageEventsPageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MycartPage {
	WebDriver driver;
	public MycartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public CheckoutPage clickOnCheckoutButton()
	{
		checkoutButton.sendKeys(Keys.CONTROL.ENTER);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
