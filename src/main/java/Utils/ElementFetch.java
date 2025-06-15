package Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.BaseCLass;

public class ElementFetch extends BaseCLass{

	
	public WebElement getElement(String identifier, String identifierValue)
	{
		switch (identifier) {
		
		case "XPATH":
			return d.findElement(By.xpath(identifierValue));
			
		case "CSS":
        	return d.findElement(By.cssSelector(identifierValue));
    	case "ID":
        	return d.findElement(By.id(identifierValue));
    	case "CLASS":
        	return d.findElement(By.className(identifierValue));
    	default:
    		return null;
		}
	
	}
	
	public List<WebElement> getElements(String identifier, String identifierValue)
	{
		switch (identifier) {
		
		case "XPATH":
			return d.findElements(By.xpath(identifierValue));
			
		case "CSS":
        	return d.findElements(By.cssSelector(identifierValue));
    	case "Id":
        	return d.findElements(By.id(identifierValue));
    	case "CLASS":
        	return d.findElements(By.className(identifierValue));
    	default:
    		return null;
		}
	
	}
}
