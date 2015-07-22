package org.roommanager.framework.pages.tablet.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.search.SearchConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class SearchPage {
	@FindBy (xpath = SearchConstant.SEARCH_ICON) 
	private WebElement searchIcon;
	@FindBy (xpath = SearchConstant.ADVANCED_BUTTON) 
	private WebElement advancedButton;
	@FindBy (xpath = SearchConstant.ROOM_NAME_TEXT_FIELD) 
	private WebElement roomNameTextField;
	@FindBy (xpath = SearchConstant.ROOM_LIST) 
	private WebElement roomList;
	@FindBy (xpath = SearchConstant.DIV_ELEMENT) 
	private WebElement divElement;
	@FindBy (xpath = SearchConstant.ROOM_NAME)
	private WebElement roomName;
	@FindBy (xpath = SearchConstant.SELECT_LOCATION) 
	private WebElement selectLocation;
	@FindBy (xpath = SearchConstant.LOCATION_NAME) 
	private WebElement locationName;
	private WebDriver driver;
	WebElement locationElements;
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * clickSearchIcon: It clicks on the search icon.
	 *        
	 * @return SearchPage
	 */
	public SearchPage clickSearchIcon(){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(searchIcon));
		searchIcon.click();
		LogManager.info("Click on Search Icon");
		return this;
	}
	
	/**
	 * clickAdvancedButton: It clicks on the advanced search button.
	 *        
	 * @return SearchPage
	 */
	public SearchPage clickAdvancedButton(){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(advancedButton));
		advancedButton.click();
		LogManager.info("Click on advanced Button");
		return this;
	}
	
	/**
	 * enterRoomName: It sets the room name for search.
	 *        
	 * @param roomName
	 *          : It represents the Room's Name
	 *          
	 * @return SearchPage
	 */
	public SearchPage enterRoomName (String roomName){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(roomNameTextField));
		roomNameTextField.clear();
		roomNameTextField.sendKeys(roomName);
		LogManager.info("RoomName: <" + roomName + "> was entered");
		return this;
	}
	
	/**
	 * getRoomByName: It gets the room by display name room.
	 *        
	 * @param roomDisplayName
	 *          : It represents the Room's Display Name
	 *          
	 * @return room
	 */
	public WebElement getRoomByName(String roomDisplayName){
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomList));
			List<WebElement> rooms = roomList.findElements(By.xpath(
					SearchConstant.DIV_ELEMENT));
			for (WebElement room : rooms) {
				(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.visibilityOf(room));
				String roomItemName = room.findElement(By.xpath(
									SearchConstant.ROOM_NAME)).getText();
				
				if (roomItemName.equals(roomDisplayName)) {
					LogManager.info("Room: <" + roomItemName
									+ "> was found on Room list");
					return room;
				}
			}
			LogManager.info("Room: <" + roomDisplayName
							+ "> wasn't found on Room list");
			return null; 
	}
	
	/**
	 * isRoomPresent: It searches the room by display name room.
	 *        
	 * @param roomDisplayName
	 *          : It represents the Room's Display Name
	 *          
	 * @return boolean
	 */
	public boolean isRoomPresent(String roomDisplayName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomList));
			List<WebElement> rooms = roomList.findElements(By.xpath(
					SearchConstant.DIV_ELEMENT));
			for (WebElement room : rooms) {
				(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.visibilityOf(room));
				String roomItemName = room.findElement(By.xpath(
									SearchConstant.ROOM_NAME)).getText();
				
				if (roomItemName.equals(roomDisplayName)) {
					LogManager.info("Room: <" + roomItemName
									+ "> was found on Room list");
					return true;
				}
			}
			LogManager.info("Room: <" + roomDisplayName
							+ "> wasn't found on Room list");
			return false;
	}
	
	
	public SearchPage selectLocation(){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(selectLocation));
		selectLocation.click();
		LogManager.info("Click on [Location] combo box");
		return this;
	}
	
	public WebElement getLocationByName(String locationDisplayName){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(selectLocation));
		
		List<WebElement> locationTable = selectLocation
				.findElements(By.xpath(SearchConstant.LOCATION_NAME));
	/*do{
		locationTable = selectLocation
				.findElements(By.xpath(SearchConstant.LOCATION_NAME));
	}
	while(locationTable.size() <= 2);*/
		for (WebElement locationElement : locationTable){
			
		String location = locationElement.getText();
		if (location.equals(locationDisplayName)) {
			LogManager.info("Location: <" + location+ "> was retrieved from Location Table");
			return locationElement;	
		}
	}
	LogManager.info("Location: <" + locationDisplayName + "> wasn't found");
	return null;
	}
	
	public SearchPage clickOnSelectLocation(String locationDisplayName){
		WebElement location = getLocationByName(locationDisplayName);
		location.click();
		/*Actions action = new Actions(driver);
		action.doubleClick(location);
		action.perform();*/
		LogManager.info("Location: <" + locationDisplayName 
				+ "> was selected");
		return this;
	}
	
}
