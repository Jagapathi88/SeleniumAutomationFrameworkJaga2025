package Test;

import org.testng.annotations.Test;

import Base.BaseCLass;
import PageEvents.LoginPageEvents;

public class NewTest extends BaseCLass   {
	
	 LoginPageEvents login = new LoginPageEvents();
  @Test
  public void f() {
	 
	  login.signIn();
	  System.out.println(5%0);
  }
}
