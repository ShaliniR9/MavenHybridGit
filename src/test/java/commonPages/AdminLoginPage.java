package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLoginPage {
WebDriver driver;
//Constructor for WebDriver methods
public AdminLoginPage(WebDriver driver)
{
	this.driver=driver; 
}
//define repository
@FindBy(name ="txtuId")
WebElement username;
@FindBy(name ="txtPword")
WebElement password;
@FindBy(xpath ="//input[@id='login']")
WebElement clickLogin;
//method for Login
public boolean verify_Login(String username, String password)
{
	this.username.sendKeys(username);
	this.password.sendKeys(password);
	this.clickLogin.click();
	String expected ="adminflow";
	String actual =driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Login Success::"+expected+"   "+actual,true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail::"+expected+"   "+actual,true);
		return false;
	}
	
}
}
