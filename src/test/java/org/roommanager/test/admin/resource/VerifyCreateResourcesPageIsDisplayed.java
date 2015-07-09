package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyCreateResourcesPageIsDisplayed extends TestBase {
	
	private String errorMessage = "The test failed because the CreateResource Page "
			+ "is not displayed";

	/**
	 * This method performs the test case: Verify that CreateResource Form  
	 * is displayed.
	 */
    @Test
    public void verifyCreateResourcePageIsDisplayed() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		CreateResourcePage create = resources.clickAddResourceButton();
		
		Assert.assertTrue(create.isCreateResourcePagePresent()
				,errorMessage);
    }
	
	

}
