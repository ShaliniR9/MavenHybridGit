package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdationPage {
WebDriver driver;
public BranchUpdationPage(WebDriver driver)
{
	this.driver = driver;
}
@FindBy(xpath = "(//img)[10]")
WebElement clickEditBtn;
@FindBy(name = "txtbnameU")
WebElement EnterBname;
@FindBy(name = "txtadd1u")
WebElement EnterAddress1;
@FindBy(name = "txtareaU")
WebElement EnterArea;
@FindBy(name = "txtzipu")
WebElement EnterZip;
@FindBy(name = "btnupdate")
WebElement clickUpdateBtn;
public boolean verify_BranchUpdate(String BranchName,String Address1,String Area,String zipCode) throws Throwable
{
	this.clickEditBtn.click();
	this.EnterBname.clear();
	this.EnterBname.sendKeys(BranchName);
	this.EnterAddress1.clear();
	this.EnterAddress1.sendKeys(Address1);
	this.EnterArea.clear();
	this.EnterArea.sendKeys(Area);
	this.EnterZip.clear();
	this.EnterZip.sendKeys(zipCode);
	this.clickUpdateBtn.click();
	Thread.sleep(3000);
	String ExpectedAlert = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	Thread.sleep(3000);
	String ActualAlert = "Branch updated";
	if(ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()))
	{
		Reporter.log(ExpectedAlert,true);
		return true;
	}
	else
	{
		Reporter.log("Branch Updation Fail",true);
		return false;
	}
}

}
