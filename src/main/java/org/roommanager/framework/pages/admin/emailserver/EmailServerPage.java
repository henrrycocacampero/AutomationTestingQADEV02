package org.roommanager.framework.pages.admin.emailserver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.emailserver.EmailServerConstant;

public class EmailServerPage {

	WebDriver driver;
	public static final String addButton = EmailServerConstant.ADD_BUTTON;
	public static final String removeButton = EmailServerConstant.REMOVE_BUTTON;
	public static final String serverButton = EmailServerConstant.SERVER_BUTTON;
	
	@FindBy (xpath = addButton) WebElement add_Button;
	@FindBy (xpath = removeButton) WebElement remove_Button;
	@FindBy (xpath = serverButton) WebElement server_Button;
	
	public EmailServerPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public CreateEmailServerPage clickAddButton(){
		(new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(add_Button));
		add_Button.click();
		return new CreateEmailServerPage(driver);
	}
	
	public String getEmailServer(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(server_Button));
	    return driver.findElement(By.xpath(addButton)).getText();
	}
	
	public boolean existsEmailServerRegistered(){
		try{
			(new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(add_Button));
			return true;
		}catch(Exception e){
			return false;
		}
	}
}