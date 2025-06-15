package PageEventsPageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement enterCountry;

	@FindBy(css = "section[class*='ta-result'] button:nth-child(3)")
	WebElement country;

	@FindBy(css = "a[class*='btnn']")
	WebElement placeOrderButton;

	public void checkout() {
		enterCountry.sendKeys("india");
		country.click();
		placeOrderButton.click();
	}

}
