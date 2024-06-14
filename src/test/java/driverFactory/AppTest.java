package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import commonPages.AdminLoginPage;
import commonPages.AdminLogoutPage;
import commonPages.BranchPage;
import commonPages.BranchUpdationPage;
import commonPages.PBBranches;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil {
	String inputpath = "C:\\Users\\smile\\Selenim_Workspace\\Maven_Hybrid\\FileInput\\DataEngine.xlsx";
	String outputpath = "C:\\Users\\smile\\Selenim_Workspace\\Maven_Hybrid\\FileOutput\\HybridResults.xlsx";
	String TCSheet = "TestCases";
	String TSSheet = "TestSteps";
	
	@Test
	public void startTest()throws Throwable
	{
		boolean res= false;
		String tcres="";
		//access Excel methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		
		//count no of rows in TCSheet and TSSheet
		int TCCount =xl.rowCount(TCSheet);
		int TSCount =xl.rowCount(TSSheet);
		Reporter.log(TCCount+"   "+TSCount);
		for(int i=1;i<=TCCount;i++)
		{
			//read module status cell
			String ModuleStatus =xl.getCellData(TCSheet, i, 2);
			if(ModuleStatus.equalsIgnoreCase("Y"))
			{
				//read tcid cell data
				String TCID =xl.getCellData(TCSheet, i, 0);
				for(int j=1;j<=TSCount;j++)
				{
					//read tsid cell data
					String TSID = xl.getCellData(TSSheet, j, 0);
					if(TCID.equalsIgnoreCase(TSID)) 
					{
						String keyWord = xl.getCellData(TSSheet, j, 3);
						if(keyWord.equalsIgnoreCase("adminLogin"))
						{
							AdminLoginPage login =PageFactory.initElements(driver, AdminLoginPage.class);
							String Para1 = xl.getCellData(TSSheet, j, 5);
							String Para2 = xl.getCellData(TSSheet, j, 6);
							res =login.verify_Login(Para1, Para2);
						}
						else if(keyWord.equalsIgnoreCase("branchCreation"))
						{
							PBBranches branch =PageFactory.initElements(driver, PBBranches.class);
							BranchPage branchcreation = PageFactory.initElements(driver, BranchPage.class);
							String Para1 = xl.getCellData(TSSheet, j, 5);
							String Para2 = xl.getCellData(TSSheet, j, 6);
							String Para3 = xl.getCellData(TSSheet, j, 7);
							String Para4 = xl.getCellData(TSSheet, j, 8);
							String Para5 = xl.getCellData(TSSheet, j, 9);
							String Para6 = xl.getCellData(TSSheet, j, 10);
							String Para7 = xl.getCellData(TSSheet, j, 11);
							String Para8 = xl.getCellData(TSSheet, j, 12);
							String Para9 = xl.getCellData(TSSheet, j, 13);
							branch.branches();
							res = branchcreation.verify_NewBranch(Para1, Para2, Para3, Para4, Para5, Para6, Para7, Para8, Para9);
						}
						else if(keyWord.equalsIgnoreCase("branchUpdation"))
						{
							PBBranches branch =PageFactory.initElements(driver, PBBranches.class);
							BranchUpdationPage updateBranch =PageFactory.initElements(driver, BranchUpdationPage.class);
							String Para1 = xl.getCellData(TSSheet, j, 5);
							String Para2 = xl.getCellData(TSSheet, j, 6);
							String Para5 = xl.getCellData(TSSheet, j, 9);
							String Para6 = xl.getCellData(TSSheet, j, 10);
							branch.branches();
							res = updateBranch.verify_BranchUpdate(Para1, Para2, Para5, Para6);
						}
						else if(keyWord.equalsIgnoreCase("adminLogout"))
						{
							AdminLogoutPage logout= PageFactory.initElements(driver, AdminLogoutPage.class);
							logout.verify_Logout();
						}
						String tsres="";
						if(res)
						{
							//if res is true write as pass into TSSheet
							tsres = "Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						else
						{
							//if res is false write as fail into TSSheet
							tsres = "Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						//if not tcres equal to null then write as pass or fail into TCSheet
						if(!tsres.equalsIgnoreCase("Fail"))
						{
							tcres = tsres;
							
						}
					}
				}
				xl.setCellData(TCSheet,i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked in status cell which test case flag N
				xl.setCellData(TCSheet, i, 3,"Blocked", outputpath);
			}
		}
	}


}
