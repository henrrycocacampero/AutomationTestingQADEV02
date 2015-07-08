package org.roommanager.test.admin.resource;

import static org.junit.Assert.assertEquals;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * The class is a test that verify DisplayName updated is displayed
 * 
 */
public class VerifyDisplayNameUpdatedIsDisplayed extends TestBase{

	 /** resourceName Name of the resource to be Created*/
	 private String resourceName = "Resource01";
	 
	 /** resourceDisplayName Display Name of the resource to be Created*/
     private String resourceDisplayName = "Resource01";
     
     /** resourceDescription Description of the resource to be Created*/
     private String resourceDescription = "Description Resource01";
     
     /** resourceIcon Icon of the resource to be Created*/
     private String resourceIcon = "";
     
     /** resourceDisplayNameUpdated Display Name of the resource to be Updated*/
	 private String resourceDisplayNameUpdated = "Resource01 Updated";

	 /**
	 * Method BeforeTest Create a resource to be updated in the test.
	 */
	@BeforeTest
	 public void BeforeTest(){
	    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}
	
	/**
	 * Method that execute the test case to verify that Display Name of a 
	 * Resource is updated
	 */
  	@Test
	public void verifyDisplayNameUpdatedIsDisplayed() throws Exception {
		String errorMessage = "The resource name is not changed";
		
		LoginPage login = new LoginPage(driver);
		HomePage homePage = login
				.clickSignInButton();
		ResourcePage resource =  homePage.selectResourcesLink();
		CreateResourcePage resourcePage = resource.doubleClickOnResourceFromTable(resourceName);
		resource = resourcePage.enterResourceDisplayName(resourceDisplayNameUpdated)
				.clickSaveResourceButton();
		String actualResourceName = resource.getResourceDisplayNameInTable(resourceDisplayNameUpdated);
		
		assertEquals(errorMessage, actualResourceName, resourceDisplayNameUpdated);
  	}
  	
  	/**
	 * Method AfterTest Delete the resource that was created in the Test.
	 */
	@AfterTest
	public void AfterTest(){
		ResourceApi.deleteResourceByName(resourceName);
	}
 
}
