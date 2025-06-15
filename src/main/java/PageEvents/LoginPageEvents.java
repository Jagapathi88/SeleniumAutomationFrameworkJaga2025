package PageEvents;

import PageObjects.LoginPageElements;
import Utils.ElementFetch;

public class LoginPageEvents {
 ElementFetch ele = new ElementFetch();

 
	public void signIn()
	{
		ele.getElement("ID", LoginPageElements.email).sendKeys("jagapathi016@gmail.com");
		ele.getElement("ID", LoginPageElements.password).sendKeys("Jagapathi@161788");
		ele.getElement("ID", LoginPageElements.login).click();;
	}
}
