package org.roommanager.framework.pages.admin.locations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.LocationsInfoConstant;

public class LocationsInfoPage extends PageFactory{
	WebDriver driver;
	@FindBy (xpath = LocationsInfoConstant.SAVE_BUTTON) 
	private WebElement saveButton;
	@FindBy (xpath = LocationsInfoConstant.CANCEL_BUTTON) 
	private WebElement cancelButton;
	@FindBy (xpath = LocationsInfoConstant.NAME_TEXT_FIELD) 
	private WebElement nameTextField;
	@FindBy (xpath = LocationsInfoConstant.DISPLAY_NAME_TEXT_FIELD) 
	private WebElement displayNameTextField;
	@FindBy (xpath = LocationsInfoConstant.DESCRIPTION_TEXT_FIELD) 
	private WebElement descriptionTextArea;
	@FindBy (xpath = LocationsInfoConstant.ADD_PARENT_LOCATION_BUTTON) 
	private WebElement addParentLocationButton;
	@FindBy (xpath = LocationsInfoConstant.EXPAND_BUTTON) 
	private WebElement expandButton;
		
	public LocationsInfoPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LocationsPage clickSaveButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.invisibilityOfElementLocated(By
					.xpath(LocationsInfoConstant.SAVE_BUTTON)));
		driver.navigate().refresh();
		return new LocationsPage(driver);
	}
	
	public LocationsInfoPage setNameTextField(String name){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(nameTextField));
		nameTextField.clear();
		nameTextField.sendKeys(name);
		return this;
	}
	
	public LocationsInfoPage setDisplayNameTextField(String displayName){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(displayNameTextField));
		displayNameTextField.clear();
		displayNameTextField.sendKeys(displayName);
		return this;
	}
	
	public LocationsInfoPage setDescriptionTextArea(String description){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(descriptionTextArea));
		descriptionTextArea.clear();
		descriptionTextArea.sendKeys(description);
		return this;
	}
	
}