package commonPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBBranches {
@FindBy(xpath = "(//img)[5]")
WebElement clickBranches;
public void branches()
{
	clickBranches.click();
}

}
