package org.roommanager.framework.pages.tablet.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.setting.ConnectionConstant;
import org.roommanager.framework.pages.tablet.common.TopMenuPage;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class ConnectionPage extends TopMenuPage{
    @FindBy (xpath = ConnectionConstant.SAVE_URL_BUTTON)
    private WebElement saveButton;
    @FindBy (xpath = ConnectionConstant.URL_TEXT_FIELD) 
    private WebElement serverUrlTextField;
    @FindBy (xpath = ConnectionConstant.NAVIGATION_LINK) 
    private WebElement navigationLink;
    @FindBy (xpath = ConnectionConstant.SUCCESSFUL_MESSAGE)
    private WebElement successfulMessage;
    By message= By.xpath(ConnectionConstant.SUCCESSFUL_MESSAGE);
    By error = By.xpath(ConnectionConstant.ERROR_MESSAGE);
    
	@FindBy(xpath = ConnectionConstant.ROOM_LOAD)
	private WebElement nameRoomLabel;
	
	private WebDriver driver;
	
	public ConnectionPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		driver.get(PropertiesReader.getLoginUrlTabletModule());
		PageFactory.initElements(driver, this);
	}

	public ConnectionPage clickSaveButton(){
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.invisibilityOfElementLocated(error));
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        LogManager.info("save Button was clicked");
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.invisibilityOfElementLocated(message));
        return this;

	}
	public Boolean isConnectionNotEstablished(String url){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(serverUrlTextField));
		Boolean Notconnection=true;
		String actualConnection=serverUrlTextField.getAttribute("value");
		if(url.equals(actualConnection)){
			Notconnection=false;
			LogManager.info("There is a connection on URL: "+ actualConnection);
		}
		else{
			LogManager.info("There isn't a connection on URL: "+ actualConnection);
		}
		return Notconnection;
	}
	
	public ConnectionPage enterServiceUrl(String url){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(serverUrlTextField));
		serverUrlTextField.clear();
		serverUrlTextField.sendKeys(url);
		LogManager.info("The Url: <" + url + "> was entered in the Url Text Field");
		return this;
	}
	
	public NavigationPage clickNavigationLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(navigationLink));
		navigationLink.click();
		return new NavigationPage(driver);
	}
	
	public String connectionMessage(){
        (new WebDriverWait(driver, 30))
        .until(ExpectedConditions.invisibilityOfElementLocated(error));
        (new WebDriverWait(driver,30))
        .until(ExpectedConditions.visibilityOf(successfulMessage));
        String successfulConnectMessage = successfulMessage.getText();
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.invisibilityOfElementLocated(message));
        LogManager.info("Successfull Message: <"+ successfulConnectMessage +"> was displayed");
        return successfulConnectMessage; 
	}
	
	 public String getRoomName(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
								.visibilityOf(nameRoomLabel));
		 String getRoomName = nameRoomLabel.getText();
		 return getRoomName ;
	 }
	


}
