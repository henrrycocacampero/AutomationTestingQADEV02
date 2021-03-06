package org.roommanager.test.tablet.search;

import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifySearchByLocation extends TestBase {
	
	/** roomName: Name of room to be used */
	private String roomName = "Room02";

	/** locationName: represents the location's name to be created */
	private String locationName = "Location Name";

	/**
	 * locationDisplayName: represents the location's display name to be created
	 */
	private String locationDisplayName = "Location Display Name";

	/** locationDescription: represents the location's description to be created */

	private String locationDescription = "Location Description";

	/**
	 * errorMessage: It contains the error message that would appear if test
	 * case fails
	 */
	private String errorMessage = "The Test failed because the Location "
			+ "couldn't be dissociated from the specified Room";

	/** connection: Name of a new ConnectionPage */
	ConnectionPage connection;

	/**
	 * This method creates a Location and associates it to a Room and creates a
	 * new connection if there is not
	 */
	@BeforeTest
	public void beforeTest() {
		if (EmailServerApi.getEmailServiceId() == null) {
			EmailServerApi.createEmailServer(
						PropertiesReader.getExchangeUserName(),
						PropertiesReader.getExchangePassWord(),
						PropertiesReader.getExchangeHostName());
		}
		LocationApi.createLocation(locationName, locationDisplayName,
				locationDescription);
		LocationApi.associateLocation(locationName, roomName);
		connection = new ConnectionPage(driver);
		String url = PropertiesReader.getRoomManagerApi();
		if (connection.isConnectionNotEstablished(url)) {
			connection.enterServiceUrl(url).clickSaveButton()
					.clickNavigationLink().clickDefaultRoomComboBox()
					.selectConferenceRoomByName(roomName).clickSaveButton();
		}
	}

	/**
	 * Test method: Verify that an specific room is displayed on [Rooms List]
	 * schedule table using the [Location] filter.
	 */
	@Test
	public void verifySearchByLocation() throws InterruptedException {

		SearchPage search = connection.clickOnSearchPageLink();
		
		search.clickAdvancedButton().selectLocation()
				.clickOnSelectLocation(locationDisplayName);

		Assert.assertTrue(search.isRoomPresent(roomName), errorMessage);

	}
	/**
	 * This method removes the Location that was created by the test
	 */
	@AfterTest
	public void afterTest() {
		LocationApi.deleteLocationByName(locationName);
	}
}
