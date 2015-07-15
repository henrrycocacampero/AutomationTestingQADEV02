package org.roommanager.framework.pages.admin.locations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.RemoveLocationsConstant;

public class RemoveLocationsPage extends PageFactory{
	
	WebDriver driver;
	@FindBy (xpath = RemoveLocationsConstant.REMOVE_BUTTON) 
	private WebElement removeButton;
	@FindBy (xpath = RemoveLocationsConstant.CANCEL_BUTTON) 
	private WebElement cancelButton;
	
	public RemoveLocationsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LocationsPage clickRemoveButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(removeButton));
		removeButton.click();
		driver.navigate().refresh();
		return new LocationsPage(driver);
	}
}
