package commonPages;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchPage {
	WebDriver driver;
	public BranchPage(WebDriver driver)
	{
		this.driver = driver;
	}
	@FindBy(xpath ="//input[@id='BtnNewBR']")
	WebElement ClickNewBranch;
	@FindBy(name = "txtbName")
	WebElement EnterBname;
	@FindBy(name = "txtAdd1")
	WebElement EnterAddress1;
	@FindBy(name = "Txtadd2")
	WebElement EnterAddress2;
	@FindBy(name = "txtadd3")
	WebElement EnterAddress3;
	@FindBy(name = "txtArea")
	WebElement EnterArea;
	@FindBy(name = "txtZip")
	WebElement EnterZipcode;
	@FindBy(name = "lst_counrtyU")
	WebElement SelectCountry;
	@FindBy(name = "lst_stateI")
	WebElement SelectState;
	@FindBy(name = "lst_cityI")
	WebElement SelectCity;
	@FindBy(name = "btn_insert")
	WebElement ClickSubmit;
	public boolean verify_NewBranch(String BranchName,String Address1,String Address2,String Address3,String Area,String Zipcode,
			String Country,String State,String City) throws Throwable
	{
		this.ClickNewBranch.click();
		this.EnterBname.sendKeys(BranchName);
		this.EnterAddress1.sendKeys(Address1);
		this.EnterAddress2.sendKeys(Address2);
		this.EnterAddress3.sendKeys(Address3);
		this.EnterArea.sendKeys(Area);
		this.EnterZipcode.sendKeys(Zipcode);
		new org.openqa.selenium.support.ui.Select(this.SelectCountry).selectByVisibleText(Country);
		new org.openqa.selenium.support.ui.Select(this.SelectState).selectByVisibleText(State);
		new org.openqa.selenium.support.ui.Select(this.SelectCity).selectByVisibleText(City);
		this.ClickSubmit.click();
		//capture alret message
		String ExpectedAlert = driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String ActualAlert = "New Branch with id";
		if(ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()))
		{
			Reporter.log(ExpectedAlert,true);
			return true;
		}
		else
		{
			Reporter.log("Branch creation Fail",true);
			return false;
		}
	}


}
