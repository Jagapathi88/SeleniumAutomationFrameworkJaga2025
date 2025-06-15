package TestPageFactory;

import org.testng.annotations.Test;

import BasePageFactory.BaseClassPageFactory;
import PageEventsPageFactory.CartPage;
import PageEventsPageFactory.CheckoutPage;
import PageEventsPageFactory.LoginPage;
import PageEventsPageFactory.MycartPage;
import PageEventsPageFactory.ProductCatalougePage;

public class NewTestPageFactory2 extends BaseClassPageFactory {
	String product = "ZARA COAT 3";
  @Test
  public void test1() {
	  
	  LoginPage loginpage = new LoginPage(getDriver());
	  ProductCatalougePage productCatalougePage= loginpage.loginMethod("virat8899@gmail.com", "Virat@161788");
	  // add item to cart
	 CartPage  cartPage= productCatalougePage.addItemTocart(product);
	 MycartPage mycartPag = cartPage.clickOnCartButton();
	 CheckoutPage checkoutPage = mycartPag.clickOnCheckoutButton();
	 checkoutPage.checkout();
	 System.out.println(5%0);
  }
  

}
