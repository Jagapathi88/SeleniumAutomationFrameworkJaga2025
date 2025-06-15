package PageEventsPageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
	 WebDriver driver;

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// locators
	
	@FindBy(id ="userEmail")
	WebElement useremail;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement loginButton;
	
	
	//page methods
	public ProductCatalougePage loginMethod(String email, String password)
	{
		useremail.sendKeys(email);
		this.password.sendKeys(password);
		loginButton.click();
		ProductCatalougePage productCatalougePage = new ProductCatalougePage(driver);
		return productCatalougePage;
	}
}
