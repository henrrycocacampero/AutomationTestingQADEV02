package org.roommanager.test.tablet.search;

import org.testng.Assert;
import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class contains a test case of Search feature
 * 
 * @author Alejandra Arteaga
 *
 */
public class VerifySearchByMinimunCapacity extends TestBase {

	/** roomName: Name of room to be used */
	private String roomName = "Room09";

	/** capacity: Capacity of the room to be assigned */
	private String capacity = "80";

	/**
	 * errorMessage: It contains the error message that would appear if test
	 * case fails
	 */
	private String errorMessage = "The test failed because the Room "
			+ "was not found by its capacity";

	/** connection: Name of a new ConnectionPage */
	ConnectionPage connection;

	/**
	 * This method associates a capacity to a Room and creates a new connection
	 * if there is not
	 */
	@BeforeTest
	public void beforeTest() {
		if (EmailServerApi.getEmailServiceId() == null) {
			EmailServerApi.createEmailServer(
					PropertiesReader.getExchangeUserName(),
					PropertiesReader.getExchangePassWord(),
					PropertiesReader.getExchangeHostName());
		}
		RoomApi.setRoomCapacity(roomName, capacity);
		connection = new ConnectionPage(driver);
		String url = PropertiesReader.getRoomManagerApi();
		if (connection.isConnectionNotEstablished(url)) {
			connection.enterServiceUrl(url).clickSaveButton()
					.clickNavigationLink().clickDefaultRoomComboBox()
					.selectConferenceRoomByName(roomName).clickSaveButton();
		}
	}

	/**
	 * Test method: Verify that a room can be searched by its capacity
	 */
	@Test
	public void verifySearchByMinimunCapacity() {

		SearchPage search = connection.clickOnSearchPageLink();
		
		search.clickAdvancedButton().enterCapacity(capacity);
		
		Assert.assertTrue(search.isRoomPresent(roomName), errorMessage);

	}

	/**
	 * This method cleans the capacity to the Room
	 */
	@AfterTest
	public void testTearDown() {
		RoomApi.setRoomCapacity(roomName, "");
	}

}
