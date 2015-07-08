package org.roommanager.framework.pages.admin.conferenceroom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ConferenceRoomTopMenuConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class ConferenceRoomTopMenu {
	@FindBy (xpath = ConferenceRoomTopMenuConstant.ROOM_INFO) 
	private WebElement roomInfoLink;
	@FindBy (xpath = ConferenceRoomTopMenuConstant.RESOURCE_ASSOCIATION) 
	private WebElement resourceAssociationLink;
	@FindBy (xpath = ConferenceRoomTopMenuConstant.OUT_ORDER_PLANNING) 
	private WebElement outOfOrderPlanningLink;
	private WebDriver driver;
	
	public ConferenceRoomTopMenu(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public RoomInfoPage clickOnRoomInfoLink(String roomName){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(roomInfoLink));
		roomInfoLink.click();
		LogManager.info("Click on Resource: Room Info Link");
		return new RoomInfoPage(driver);
	}
}
