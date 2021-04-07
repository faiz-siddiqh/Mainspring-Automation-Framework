package core;

import java.util.List;

import org.openqa.selenium.WebElement;

import core.Details_Util;

/**
 * 
 * @author Faiz Ahmed Siddiqh
 *
 */
public class HomePage_Util {

	public Details_Util details = new Details_Util();

	/**
	 * To SetUp the necessary drivers,files before the execution of
	 * methods[APPLICABLE FOR ALL THE MODULES]
	 * 
	 * @param className
	 *
	 */
	public void setUp(String className) {
		BaseUtils.common.setClassName(className);
		BaseUtils.testData.setTestFile("Mainspring");
		BaseUtils.setUp();
	}

	/**
	 * To Launch and login to the BaseUrl or Homepage of the mainspring app
	 * 
	 * @param testName-Name of the current testmethod
	 */
	public void launchAndLogin(String testName) {
		BaseUtils.common.setMethodName(testName);
		BaseUtils.common.setExtentTest(testName);
		BaseUtils.setUpDriver();
		BaseUtils.common.appLogin();

	}

	/**
	 * To create a new Risk
	 * 
	 * @param riskStage
	 * @param description
	 * @param dateToBeSelected
	 * 
	 * @param testName-Name    of the Risk to be created
	 */
	public void createANewRisk(String riskName, String ImpactArea, String ImpactScore1, String ImpactScore2,
			String cause, String riskStage, String description, String dateToBeSelected) {

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("Riskpage-AddRisk-Btn").replaceAll("*", "span")),
				"Click And Wait on Add risk btn");
		BaseUtils.waitForThePageToLoad();
		BaseUtils.clickAndTypeAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Name")),
				riskName, "Entering the Name of the Risk");

		List<WebElement> impactAreaValues = BaseUtils.getElements(BaseUtils.locators.getLocator("Details-ImpactArea"),
				"xpath");

		if (ImpactArea.equalsIgnoreCase("Delivery Impact")) {
			BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
			details.updateImpactAreaForDeliveryImpact(ImpactScore1, ImpactScore2, cause);
		} else if (ImpactArea.equalsIgnoreCase("Regulatory Impact")) {
			BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
			details.updateImpactAreaForRegulatoryImpact(ImpactScore1, ImpactScore2, cause);
		} else if (ImpactArea.equalsIgnoreCase("Reputational Impact")) {
			BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
			details.updateImpactAreaForReputationalImpact(ImpactScore1, ImpactScore2, cause);
		} else if (ImpactArea.equalsIgnoreCase("Technology Infrastructure and Data Security Impact")) {
			BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
			details.updateImpactAreaForTechnologyInfrastructureImpact(ImpactScore1, ImpactScore2, cause);
		}

		BaseUtils.selectFromDropdown(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-RiskStage")),
				riskStage);
		BaseUtils.clickAndTypeAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Description")),
				description, "Click and Type in description");
		details.updateTargetClosureDate(dateToBeSelected);
		BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Save-Btn"));
		BaseUtils.waitForThePageToLoad();

	}

}