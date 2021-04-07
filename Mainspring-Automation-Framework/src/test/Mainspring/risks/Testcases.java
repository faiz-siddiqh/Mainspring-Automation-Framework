package risks;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.BaseUtils;
import core.HomePage_Util;
import core.BaseUtils.locators;
import core.Details_Util;

public class Testcases {

	private static HomePage_Util homepage = new HomePage_Util();
	private static Details_Util detailsTab = new Details_Util();

	/**
	 * Before Class setup method to initiate and setup prerequesites for running
	 * tests
	 */
	@BeforeClass
	public void setUp() {
		homepage.setUp("Risks");
	}

	/**
	 * @author Faiz-Ahmed Siddiqh k HomePage and Details Tab
	 */
	/**
	 * Testcase ID=10001 TestCase Description: To navigate to Risks module in
	 * mainspring application
	 * 
	 * @param method
	 */
	@Test(priority = 0)
	public void navigateToRiskPage_10001(Method method) {
		homepage.launchAndLogin(method.getName());
		BaseUtils.navigateToRisksPage();

	}

	/**
	 * Testcase ID=10002 TestCase Description: Test if user is able to navigate to a
	 * Risk by searching in the search bar
	 * 
	 * @param method
	 */
	@Test(priority = 1)
	public void createARisk_10002(Method method) {
		homepage.launchAndLogin(method.getName());

		// Testdata from the excel file
		String nameOfTheRisk = BaseUtils.testData.getTestData("Risk_Name");
		String impactArea = BaseUtils.testData.getTestData("Impact_Area");
		String deliverImpactScore = BaseUtils.testData.getTestData("delivery_Impact_Score");
		String livelihoodScore = BaseUtils.testData.getTestData("Livelihood_Score");
		String cause = BaseUtils.testData.getTestData("Cause");
		String riskStage = BaseUtils.testData.getTestData("Risk_Stage");
		String commentDescription = BaseUtils.testData.getTestData("Comments");
		String targetClosureDate = BaseUtils.testData.getTestData("Target_Closure_Date");

		BaseUtils.navigateToRisksPage();
		homepage.createANewRisk(nameOfTheRisk, impactArea, deliverImpactScore, livelihoodScore, cause, riskStage,
				commentDescription, targetClosureDate);

	}

	/**
	 * Testcase ID=10003 TestCase Description: Test if user is able to filter risks
	 * for overall status closed
	 * 
	 * @param method
	 */
	public void filterRisksWithStatusClosed_10003(Method method) {
		homepage.launchAndLogin(method.getName());
		BaseUtils.navigateToRisksPage();
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Advance Filter-Btn")),
				"Click on the filter button");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filter-closed")),
				"Select closed  from the filters");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Apply-Btn")),
				"Select closed  from the filters");
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10004 TestCase Description: test the functionality of "clear are
	 * filters" button,whether the user is able to clear filters
	 * 
	 * @param method
	 */
	public void testAllClearFiltersBtn_10004(Method method) {
		homepage.launchAndLogin(method.getName());
		BaseUtils.navigateToRisksPage();
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Advance Filter-Btn")),
				"Click on the filter button");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filter-closed")),
				"Select closed  from the filters");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Apply-Btn")),
				"Select closed  from the filters");
		BaseUtils.waitForThePageToLoad();
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Filter-Clear-Btn")),
				"Click on clear all filters Btn");
	}

	/**
	 * Testcase ID=10005 TestCase Description: Test if user is able to search a risk
	 * from list olf risks
	 * 
	 * @param method
	 */
	@Test
	public void searchARisk_10005(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSearched = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToRisksPage();

		BaseUtils.waitForTheElementToBePresent(10,
				BaseUtils.getElementByXpath(locators.getLocator("Riskpage-Search-Input")),
				"Wait and enter the risk name in search field");
		BaseUtils.clickAndTypeAndWait(BaseUtils.getElementByXpath(locators.getLocator("Riskpage-Search-Input")),
				riskToBeSearched, "Entering the risk name");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(locators.getLocator("Riskpage-Search-Button")),
				"Click on the search button");
		BaseUtils.waitForThePageToLoad();
		String riskToBeSelected = locators.getLocator("Riskpage-SelectRisk").replaceAll("Risk", riskToBeSearched);
		Assert.assertTrue(BaseUtils.isElementPresent(riskToBeSelected, "Risk is displayed"), "Risk is not displayed");

	}

	/**
	 * Testcase ID=10006 TestCase Description: Test if user is able to navigate to a
	 * Risk by searching in the search bar
	 * 
	 * @param method
	 */
	@Test(priority = 3)
	public void navigateToTheCreatedRisk_10006(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);

	}

	/**
	 * Testcase ID=10007 TestCase Description: Test if user is able to filter Risk
	 * by Id
	 * 
	 * @param method
	 */
	@Test
	public void filterRisksById_10007(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToRisksPage();
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filters-id-click")),
				"Click on the id field");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filter-arrow-Btn")),
				"Click on the arrow down");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filters")),
				"Click on the filters down");
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filters-id-input")),
				riskToBeSelected, "Click And Type the id in the filter");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filters-id-apply")),
				"Click on the Apply Button");
		BaseUtils.waitForThePageToLoad();
		String riskSelected = locators.getLocator("Riskpage-SelectRisk").replaceAll("Risk", riskToBeSelected);
		Assert.assertTrue(BaseUtils.isElementPresent(riskSelected, "Risk is displayed"), "Risk is not displayed");
	}

	/**
	 * Testcase ID=10008 TestCase Description: Test if user is able to filter Risk
	 * by Risk Name
	 * 
	 * @param method
	 */
	@Test
	public void filterRisksByName_10008(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("Risk_Name");
		BaseUtils.navigateToRisksPage();
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("Riskpage-filters-id-click").replaceAll("SEQNUMBER", "NAME")),
				"Click on the name field");
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("Riskpage-filter-arrow-Btn").replaceAll("SEQNUMBER", "NAME")),
				"Click on the arrow down");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filters-name")),
				"Click on the filters down");
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filters-id-name")),
				riskToBeSelected, "Click And Type the id in the filter");
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-filters-name-apply")),
				"Click on the Apply Button");
		BaseUtils.waitForThePageToLoad();
		String riskSelected = locators.getLocator("Riskpage-SelectRisk").replaceAll("Risk", riskToBeSelected);
		Assert.assertTrue(BaseUtils.isElementPresent(riskSelected, "Risk is displayed"), "Risk is not displayed");
	}

	/**
	 * Testcase ID=10009 TestCase Description: Test if user is able to filter Risk
	 * by Overall status
	 * 
	 * @param method
	 */
	@Test
	public void filterRisksByOverallStatus_10009(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("OverallStatus");
		BaseUtils.navigateToRisksPage();
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(
				BaseUtils.locators.getLocator("Riskpage-filter-arrow-Btn").replaceAll("SEQNUMBER", "OVERALLSTATUS")),
				"Click on the arrow down IN Overall status");
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("Riskpage-filters").replaceAll("1153", "1083")),
				"Click on the filters ");

		BaseUtils.findElementAndClick(
				BaseUtils.getElements(BaseUtils.locators.getLocator("Riskpage-filters-Overallstatus"), "xpath"),
				riskToBeSelected);

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("Riskpage-filters-name-apply").replaceAll("1125", "1066")),
				"Click on the Apply Button");
		BaseUtils.waitForThePageToLoad();
	}

	/**
	 * Testcase ID=10010 TestCase Description: Test if user is able to filter Risk
	 * using multiple sort button
	 * 
	 * @param method
	 */
	@Test
	public void filterRisksByMultipleSort_10010(Method method) {
		homepage.launchAndLogin(method.getName());

		String columnType = BaseUtils.testData.getTestData("Column_Type");
		String sortType = BaseUtils.testData.getTestData("Sort_Type");
		BaseUtils.navigateToRisksPage();
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-MutlipleSort-Btn")),
				"Click on the Multiple Sort button in Menu Bar");
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-MutlipleSort-Arrowdown")),
				"Click on the arrow down ");
		BaseUtils.findElementAndClick(
				BaseUtils.getElements(BaseUtils.locators.getLocator("Riskpage-MutlipleSort-PrimarySort-col1"), "xpath"),
				columnType);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(
				BaseUtils.locators.getLocator("Riskpage-MutlipleSort-Arrowdown").replaceAll("Column", "OrderBy")),
				"Click on the filters ");

		BaseUtils.findElementAndClick(BaseUtils.getElements(
				BaseUtils.locators.getLocator("Riskpage-MutlipleSort-PrimarySort-col1").replaceAll("1173", "1175"),
				"xpath"), sortType);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Go-Btn")),
				"Click on the Go Button");
		BaseUtils.waitForThePageToLoad();
	}

	/**
	 * Testcase ID=10011 TestCase Description: Produce error while user is trying to
	 * download a risk without selecting a risk from the list
	 * 
	 * @param method
	 */
	@Test
	public void produceErrorOnDownloadingRisk_10011(Method method) {
		homepage.launchAndLogin(method.getName());
		BaseUtils.navigateToRisksPage();
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Download-Btn")),
				"Click on the Download risk Button");
		String actualErrorMessage = BaseUtils.locators.getLocator("Riskpage-Download-Error-Message");
		String expectedErrorMessage = BaseUtils.alerts.getAlertMessage();

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}

	/**
	 * Testcase ID=10012 TestCase Description: Produce error while user is trying to
	 * update a risk without risk name
	 * 
	 * @param method
	 */
	@Test(priority = 4)
	public void produceErrorWhileUpdatingRiskWithoutName_10012(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.clickAndClearAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Name")),
				"Click On Name field clear and Wait");

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Save-Btn")),
				"Click on the Save Button");
		BaseUtils.captureScreenshot();
		String actualErrorMessage = BaseUtils.locators.getLocator("Details-Name-ErrorMessage");
		String expectedErrorMessage = BaseUtils.alerts.returnMessageAndAccept();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Return-Btn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10013 TestCase Description: Produce error while user is trying to
	 * update a risk without risk Stage
	 * 
	 * @param method
	 */
	@Test

	public void produceErrorWhileUpdatingRiskWithoutRiskStage_10013(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.selectFromDropdown(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-RiskStage")),
				" --None-- ");

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Save-Btn")),
				"Click on the Save Button");
		BaseUtils.captureScreenshot();
		String actualErrorMessage = BaseUtils.locators.getLocator("Details-RiskStage-ErrorMessage");
		String expectedErrorMessage = BaseUtils.alerts.returnMessageAndAccept();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Return-Btn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10014 TestCase Description: Produce error while user is trying to
	 * update a risk without risk Source
	 * 
	 * @param method
	 */
	@Test
	public void produceErrorWhileUpdatingRiskWithoutRiskSource_10014(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("Details-Impact-Score").replaceAll("DN_Score", "risksource")),
				" --None-- ");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Save-Btn")),
				"Click on the Save Button");
		BaseUtils.captureScreenshot();
		String actualErrorMessage = BaseUtils.locators.getLocator("Details-RiskSource-ErrorMessage");
		String expectedErrorMessage = BaseUtils.alerts.returnMessageAndAccept();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Return-Btn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10015 TestCase Description: Produce error while user is trying to
	 * update a risk with Invalid date
	 * 
	 * @param method
	 */
	@Test
	public void produceErrorWhileUpdatingRiskWithInvalidDate_10015(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		String dateToBeSelected = BaseUtils.testData.getTestData("TargetClosureDate");

		BaseUtils.navigateToARisk(riskToBeSelected);
		detailsTab.updateTargetClosureDate(dateToBeSelected);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Save-Btn")),
				"Click on the Save Button");
		BaseUtils.waitForThePageToLoad();
		BaseUtils.captureScreenshot();
		String actualErrorMessage = BaseUtils.locators.getLocator("Details-TargetClosureDate-ErrorMessage");
		String expectedErrorMessage = BaseUtils.alerts.returnMessageAndAccept();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Return-Btn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10016 TestCase Description: Produce error while user is trying to
	 * update a risk without Description
	 * 
	 * @param method
	 */
	@Test
	public void produceErrorWhileUpdatingRiskWithoutDescription_10016(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndClearAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Description")),
				"Click On Description field ,clear and Wait");

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Save-Btn")),
				"Click on the Save Button");
		BaseUtils.waitForThePageToLoad();
		BaseUtils.captureScreenshot();
		String actualErrorMessage = BaseUtils.locators.getLocator("Details-Description-ErrorMessage");
		String expectedErrorMessage = BaseUtils.alerts.returnMessageAndAccept();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Return-Btn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10017 TestCase Description: Test the functionality of Maximise
	 * Screen Btn.
	 * 
	 * @param method
	 */
	@Test(priority = 0)
	public void testMaximiseBtn_10017(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Maximise-Btn")),
				"Click on the Maximise to full screen Button");
		BaseUtils.waitForThePageToLoad();
		BaseUtils.captureScreenshot();

		BaseUtils
				.clickAndWait(
						BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Maximise-Btn")
								.replaceAll("out", "in eformIcon_mode_fullscreen")),
						"Click on the Minimise full screen Button");
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10018 TestCase Description: Test if User is able to navigate to
	 * the Guidelines page by clicking the link on details Tab
	 * 
	 * @param method
	 */
	@Test(priority = 1)
	public void testGuidelinesLink_10018(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-GuideLinesLink")),
				"Click on the Link to navigate to Guidelines page");
		BaseUtils.switchToHandle();
		BaseUtils.waitForThePageToLoad();

		Assert.assertTrue(BaseUtils.isElementPresent(BaseUtils.locators.getLocator("Details-GuideLines-Landingpage"),
				"User is on Guidelines Page"), "User is not on Guidelines Page");
		BaseUtils.captureScreenshot();
		BaseUtils.returnToParentHandle();
		BaseUtils.waitForThePageToLoad();

	}

	/**
	 * Testcase ID=10019 TestCase Description: Validate the ID field displayed in
	 * the details Tab *
	 * 
	 * @param method
	 */
	@Test
	public void validateRiskId_10019(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();
		String riskDisplayed = BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Id-field")).getText();
		Assert.assertEquals(riskToBeSelected, riskDisplayed);

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Return-Btn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();

	}

	/**
	 * Testcase ID=10020 TestCase Description: Test if user is able to Scroll down
	 * and up the page
	 * 
	 * @param method
	 */
	@Test
	public void testScrollFunctionality_10020(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();
		BaseUtils.scrollToView(250);
		BaseUtils.captureScreenshot();
		BaseUtils.scrollToView(-200);

		BaseUtils.captureScreenshot();

		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-Return-Btn")),
				"Click on the Return Button");

	}

	/**
	 * Testcase ID=10021 TestCase Description: Test the functionality of the
	 * financial Impact Check Box btn
	 * 
	 * @param method
	 */
	@Test
	public void testFinancialImpactBtn_10021(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();

		String financialImpactBtn = BaseUtils.locators.getLocator("");
		Assert.assertTrue(BaseUtils.isElementPresentAndClickable(BaseUtils.getElementByXpath(financialImpactBtn)),
				"Financial Impact Button is not present/Clickable");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(financialImpactBtn),
				"Clicking on the Financial Impact CheckBox to verify");

	}

	/**
	 * Testcase ID=10022 TestCase Description: Test the functionality of the Push
	 * Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void testPushRiskToProgramBtn_10022(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();

		String pushRiskToProgramBtn = BaseUtils.locators.getLocator("");
		Assert.assertTrue(BaseUtils.isElementPresentAndClickable(BaseUtils.getElementByXpath(pushRiskToProgramBtn)),
				"Push Risk to Program Button is not present/Clickable");
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(pushRiskToProgramBtn),
				"Clicking on the Push Risk to Program CheckBox to verify");

	}

	/**
	 * Testcase ID=10023 TestCase Description: Test Risk Score for "Delivery Impact"
	 * value in Impact Area field
	 * 
	 * @param method
	 */
	@Test
	public void testRiskScoreForDeliveryImpact_10023(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		String ImpactArea = BaseUtils.testData.getTestData("Impact_Area");
		String deliveryImpactScore = BaseUtils.testData.getTestData("delivery_Impact_Score");
		String livelihoodScore = BaseUtils.testData.getTestData("Livelihood_Score");
		String cause = BaseUtils.testData.getTestData("Cause");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();
		List<WebElement> impactAreaValues = BaseUtils.getElements(BaseUtils.locators.getLocator("Details-ImpactArea"),
				"xpath");
		BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
		detailsTab.updateImpactAreaForDeliveryImpact(deliveryImpactScore, livelihoodScore, cause);
		BaseUtils.waitForThePageToLoad();
		String riskScoreValue = BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Risk-Score"))
				.getText();

		int productOfScore = Integer.parseInt(deliveryImpactScore) * Integer.parseInt(livelihoodScore);
		int riskScore = Integer.parseInt(riskScoreValue);

		Assert.assertEquals(productOfScore, riskScore);

	}

	/**
	 * Testcase ID=10024 TestCase Description: Test Risk Score for "Regulatory
	 * Impact" value in Impact Area field
	 * 
	 * @param method
	 */
	@Test
	public void testRiskScoreForRegulatoryImpact_10024(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		String ImpactArea = BaseUtils.testData.getTestData("Impact_Area");
		String regulatoryImpactScore = BaseUtils.testData.getTestData("Regulatory_Impact_Score");
		String livelihoodScore = BaseUtils.testData.getTestData("Livelihood_Score");
		String cause = BaseUtils.testData.getTestData("Cause");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();
		List<WebElement> impactAreaValues = BaseUtils.getElements(BaseUtils.locators.getLocator("Details-ImpactArea"),
				"xpath");
		BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
		detailsTab.updateImpactAreaForRegulatoryImpact(regulatoryImpactScore, livelihoodScore, cause);
		BaseUtils.waitForThePageToLoad();
		String riskScoreValue = BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Risk-Score"))
				.getText();

		int productOfScore = Integer.parseInt(regulatoryImpactScore) * Integer.parseInt(livelihoodScore);
		int riskScore = Integer.parseInt(riskScoreValue);

		Assert.assertEquals(productOfScore, riskScore);

	}

	/**
	 * Testcase ID=10025 TestCase Description: Test Risk Score for "Reputational
	 * Impact" value in Impact Area field
	 * 
	 * @param method
	 */
	@Test
	public void testRiskScoreForReputationalImpact_10025(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		String ImpactArea = BaseUtils.testData.getTestData("Impact_Area");
		String reputationalImpactScore = BaseUtils.testData.getTestData("Reputational_Impact_Score");
		String livelihoodScore = BaseUtils.testData.getTestData("Livelihood_Score");
		String cause = BaseUtils.testData.getTestData("Cause");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();
		List<WebElement> impactAreaValues = BaseUtils.getElements(BaseUtils.locators.getLocator("Details-ImpactArea"),
				"xpath");
		BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
		detailsTab.updateImpactAreaForReputationalImpact(reputationalImpactScore, livelihoodScore, cause);
		BaseUtils.waitForThePageToLoad();
		String riskScoreValue = BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Risk-Score"))
				.getText();

		int productOfScore = Integer.parseInt(reputationalImpactScore) * Integer.parseInt(livelihoodScore);
		int riskScore = Integer.parseInt(riskScoreValue);

		Assert.assertEquals(productOfScore, riskScore);

	}

	/**
	 * Testcase ID=10026 TestCase Description: Test Risk Score for "Technology
	 * Infrastructure and Data Security Impact[TIDS]" value in Impact Area field
	 * 
	 * @param method
	 */
	@Test
	public void testRiskScoreForTIDSImpact_10026(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		String ImpactArea = BaseUtils.testData.getTestData("Impact_Area");
		String techInfraImpactScore = BaseUtils.testData.getTestData("TechInfra_Impact_Score");
		String livelihoodScore = BaseUtils.testData.getTestData("Livelihood_Score");
		String cause = BaseUtils.testData.getTestData("Cause");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();
		List<WebElement> impactAreaValues = BaseUtils.getElements(BaseUtils.locators.getLocator("Details-ImpactArea"),
				"xpath");
		BaseUtils.findElementAndClick(impactAreaValues, ImpactArea);
		detailsTab.updateImpactAreaForTechnologyInfrastructureImpact(techInfraImpactScore, livelihoodScore, cause);
		BaseUtils.waitForThePageToLoad();
		String riskScoreValue = BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Details-Risk-Score"))
				.getText();

		int productOfScore = Integer.parseInt(techInfraImpactScore) * Integer.parseInt(livelihoodScore);
		int riskScore = Integer.parseInt(riskScoreValue);

		Assert.assertEquals(productOfScore, riskScore);

	}

	/**
	 * Testcase ID=10027 TestCase Description: Test the functionality of the Push
	 * Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void testAddNewRiskBtn_10027(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.waitForThePageToLoad();

		String AddNewRiskBtn = BaseUtils.locators.getLocator("");
		Assert.assertTrue(BaseUtils.isElementPresentAndClickable(BaseUtils.getElementByXpath(AddNewRiskBtn)),
				"Push Risk to Program Button is not present/Clickable");

	}

	/**
	 * --------------------------------------------------------------------------------------------------------------
	 */

	/**
	 * @author Nazneen Shaik Mitigation and Contigency And Cost Benifit Analysis
	 */
	/**
	 * 
	 * Testcase ID=10028 TestCase Description: Test the functionality of the click
	 * button Mitigation and contingency
	 * 
	 * @param method
	 */
	@Test
	public void testMitigationandContingency_10028(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.clickAndWait(
				BaseUtils.getElement(BaseUtils.locators.getLocator("MitigationandContingency-click"), "linkText"));

	}

	/**
	 * Testcase ID=10029 TestCase Description: Error message is not appearing when
	 * date is cleared Risk
	 * 
	 * @param method
	 */
	@Test
	public void testMitigationTarget_closuredate_10029(Method method) {

		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		String dateToBeSelected = BaseUtils.testData.getTestData("Mitigation Target Closure Date");

		BaseUtils.navigateToARisk(riskToBeSelected);
		detailsTab.updateMitigationTargetClosureDate(dateToBeSelected);
		BaseUtils.clickAndClearAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("MitigationandContingency-Mitigationtargetclosuredate")),
				"clearingdate");

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("MitigationandContingency-Savebtn")),
				"Click on the Save Button");
		BaseUtils.waitForThePageToLoad();
		BaseUtils.captureScreenshot();

	}

	/**
	 * Testcase ID=10030 TestCase Description: Test the functionality of the
	 * dropdown Risk
	 * 
	 * @param method
	 */

	@Test
	public void testMitigation_Status_10030(Method method) {
		homepage.launchAndLogin(method.getName());

		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("MitigationandContingency-Mitigationstatus")),
				"WIP");
	}

	/**
	 * Testcase ID=10031 TestCase Description: Test the functionality by giving in
	 * description field Risk
	 * 
	 * @param method
	 */
	@Test
	public void testMitigationplan_Description_10031(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("MitigationandContingency-Mitigationplandescription")),
				"plan to complete project-107", "clickAndtype");

	}

	/**
	 * Testcase ID=10032 TestCase Description: Test the functionality of the
	 * calender(closuredate) Risk
	 * 
	 * @param method
	 */
	@Test
	public void testContingencyTarget_closuredate_10032(Method method) {

		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		String dateToBeSelected = BaseUtils.testData.getTestData("Contingency Target Closure Date");

		BaseUtils.navigateToARisk(riskToBeSelected);
		detailsTab.updateMitigationTargetClosureDate(dateToBeSelected);
		BaseUtils.clickAndClearAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("MitigationandContingency-Contingencytargetclosuredate")),
				"clearingdate");

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("MitigationandContingency-Savebtn")),
				"Click on the Save Button");
		BaseUtils.waitForThePageToLoad();
		BaseUtils.captureScreenshot();

	}

	/**
	 * Testcase ID=10033 TestCase Description: Test the functionality of the
	 * dropdown Risk
	 * 
	 * @param method
	 */
	@Test
	public void testContingency_Status_10033(Method method) {
		homepage.launchAndLogin(method.getName());

		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.selectFromDropdown(
				BaseUtils
						.getElementByXpath(BaseUtils.locators.getLocator("MitigationandContingency-Contingencystatus")),
				"Not Yet Identified");
	}

	/**
	 * Testcase ID=10034 TestCase Description: Test the functionality of the
	 * description field Risk
	 * 
	 * @param method
	 */
	@Test
	public void testContingencyplan_Description_10034(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("MitigationandContingency-Contingencyplandescription")),
				"continuing the project-107", "clickAndtypemethod");
	}

	/**
	 * Testcase ID=10035 TestCase Description: click on the Costbenefitanalysis Risk
	 * 
	 * @param method
	 */
	@Test
	public void testCostBenefit_Analysisclick_10035(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.clickAndWait(
				BaseUtils.getElement(BaseUtils.locators.getLocator("CostBenifitAnalysis-click"), "linkText"));

	}

	/**
	 * Testcase ID=10036 TestCase Description: Test the functionality of the
	 * decision field Risk
	 * 
	 * @param method
	 */
	@Test
	public void testCostbenefitAnalysis_Decision_10036(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("CostBenifitAnalysis-Costbenefitanalysisdecision")),
				"no analysis", "benefitAnalysisdisplayed");
	}

	/**
	 * Testcase ID=10037&10038 TestCase Description: Test the functionality of
	 * Riskcost and capture error message Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void produceErrorwhileupdatingalphanumeric_10037_10038(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.clickAndClearAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Riskcost")),
				"Click On Riskcost field clear and Wait");

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Savebtn")),
				"Click on the Save Button");
		BaseUtils.captureScreenshot();
		String actualErrorMessage = BaseUtils.locators.getLocator("Riskcost-ErrorMessage");
		String expectedErrorMessage = BaseUtils.alerts.returnMessageAndAccept();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Returnbtn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();
		BaseUtils.waitForThePageToLoad();
	}

	/**
	 * Testcase ID=10039 TestCase Description: Test the functionality by giving
	 * numeric values * Risk to Program btn
	 * 
	 * @param method
	 */

	@Test
	public void updatenumericvalues_Riskcost_10039(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Riskcost")), "1000",
				"Riskcostgiven");

	}

	/**
	 * Testcase ID=10040 TestCase Description: Test the functionality of the
	 * dropdown Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void updateriskcost_currency_10040(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("CostBenifitAnalysis-Mitigation/Riskcostcurrency")),
				"INR India Rupees");
	}

	/**
	 * Testcase ID=10041 TestCase Description: Test the functionality of the
	 * description field Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void updateCostbenefitanalysis_Description_10041(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(
						BaseUtils.locators.getLocator("CostBenifitAnalysis-Costbenefitanalysisdescription")),
				"figuring out", "Riskcostdescription");

	}

	/**
	 * Testcase ID=10042&10043 TestCase Description: capturing error message by
	 * giving alphanumeric Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void produceErrorwhileupdatingalphanumericofmitigationcost_10042_10043(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.clickAndClearAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Mitigationcost")),
				"Click On Mitigationcost field clear and Wait");

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Savebtn")),
				"Click on the Save Button");
		BaseUtils.captureScreenshot();
		String actualErrorMessage = BaseUtils.locators.getLocator("Mitigationcost-ErrorMessage");
		String expectedErrorMessage = BaseUtils.alerts.returnMessageAndAccept();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Returnbtn")),
				"Click on the Return Button");
		BaseUtils.alerts.acceptAlert();
		BaseUtils.waitForThePageToLoad();
	}

	/**
	 * Testcase ID=10044 TestCase Description: Test the functionality of the
	 * description field Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void updatenumericvalues_Mitigationcost_10044(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Mitigationcost")),
				"2000", "Mitigationcostgiven");
	}

	/**
	 * Testcase ID=10045 TestCase Description: Test the functionality of the
	 * savebutton Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void SaveCostbenefitanalysis_10045(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(
				BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("CostBenifitAnalysis-Savebtn")));

	}

	/**
	 * --------------------------------------------------------------------------------------------------------------
	 */

	/**
	 * @author Swapnadip Haldar- Comments ,LinkedCards and Attachments /** Testcase
	 *         ID=10046 TestCase Description: Test the functionality of the comment
	 *         field Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void updateCommentfield_10066(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Comments-click")),
				"This is about module", "Commentgiven");
	}

	/**
	 * Testcase ID=10067 TestCase Description: Test the functionality of the
	 * Addcommentbutton Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void clickonAddcomment_10067(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Addcomment")));

	}

	/**
	 * Testcase ID=10068 TestCase Description: Test the functionality of the
	 * Addreplybutton Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void AddonreplyButton_10068(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndTypeAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Replycomment")),
				"This is extra module", "Commentssgiven");

	}

	/**
	 * Testcase ID=10069 TestCase Description: Test the functionality of the add
	 * comment replybutton Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void addcommentfor_replyBtn_10069(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("AddcommentforReplyBtn")));

	}

	/**
	 * Testcase ID=10050 TestCase Description: Test the functionality of the
	 * Hidebutton Risk to Program btn
	 * 
	 * @param method
	 */
	@Test
	public void clickon_Hidereply_10070(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("HideComment")));

	}

	@Test
	public void Attachmentsadd_File_10071(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndClearAndTypeAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("AddFiles")),
				"C:\\Users\\nazshaik\\Downloads\\New folder\\mars", "clickAndtypemethod");
	}

	@Test
	public void Clickondeletebtn_10072(Method method) {

		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("deleteFiles")));
		BaseUtils.alerts.acceptAlert();

	}

	@Test
	public void Clickoncheckbox_File_10073(Method method) {

		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("clickcheckBox")));

	}

	@Test
	public void Clickondeletebtnof_Selectedfile_10074(Method method) {

		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("deleteFiles")));
		BaseUtils.alerts.acceptAlert();
	}

	@Test
	public void Clickoncheckboxdownload_File_10075(Method method) {

		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("clickcheckBoxfile")));
	}

	@Test
	public void Clickondownloadbtnof_Selectedfile_10056(Method method) {

		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");

		BaseUtils.navigateToARisk(riskToBeSelected);
		BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("downloadBtn")));

	}

	/**
	 * ----------------------------------------------------------------------------------------------------------------------------------------------
	 * Previous Versions and Activity Log -@author Gourab Dutta
	 * 
	 */

	// Previous version element
	@Test

	public void testPreviousVersionBtn_10078(Method method) {
		homepage.launchAndLogin(method.getName());
		String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
		BaseUtils.navigateToARisk(riskToBeSelected);

		BaseUtils.getElements(BaseUtils.locators.getLocator("PreviousVersions-PreviousVersionBtn"), "xpath");

	}

	// maximize button
	@Test
		public void testPreviousVersionMaximizeBtn_10079(Method method) {
			homepage.launchAndLogin(method.getName());
			String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
			BaseUtils.navigateToARisk(riskToBeSelected);

			BaseUtils.clickAndWait(BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("PreviousVersion-Maximise-Btn")),
					"Click on the Maximise to full screen Button");
			BaseUtils.waitForThePageToLoad();
			
			
		}

	// version difference button
	@Test
		public void testPreviousVersionDifferenceBtn_10084(Method method) {
			homepage.launchAndLogin(method.getName());
			String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
			BaseUtils.navigateToARisk(riskToBeSelected);
			
			BaseUtils.getElements(BaseUtils.locators.getLocator("PreviousVersions-VersionDiffbtn"), "xpath");

			
		}

	// any version link
	@Test
		public void testPreviousVersionLink_10090(Method method) {
			homepage.launchAndLogin(method.getName());
			String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
			BaseUtils.navigateToARisk(riskToBeSelected);
			BaseUtils.waitForThePageToLoad();

			String PreviousVersionBtn = BaseUtils.locators.getLocator("");
			Assert.assertTrue(BaseUtils.isElementPresentAndClickable(BaseUtils.getElementByXpath(PreviousVersionBtn)),
					"Financial Impact Button is not present/Clickable");
			BaseUtils.clickAndWait(BaseUtils.getElementByXpath(PreviousVersionBtn),
					"Clicking on the Version's CheckBox to verify");

		}

	// Activity Log page

	// Activity log element
	@Test
		public void testActivityLogBtn_10093(Method method) {
			homepage.launchAndLogin(method.getName());
			String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
			BaseUtils.navigateToARisk(riskToBeSelected);
			
			BaseUtils.getElements(BaseUtils.locators.getLocator("ActivityLog-activitylogBtn"), "xpath");

			
		}

	// current version link
	@Test
		public void testActivityLogCurrentVersion_10094(Method method) {
			homepage.launchAndLogin(method.getName());
			String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
			BaseUtils.navigateToARisk(riskToBeSelected);
			BaseUtils.waitForThePageToLoad();

			String currentVersionLink = BaseUtils.locators.getLocator("");
			Assert.assertTrue(BaseUtils.isElementPresentAndClickable(BaseUtils.getElementByXpath(currentVersionLink)),
					"Financial Impact Button is not present/Clickable");
			BaseUtils.clickAndWait(BaseUtils.getElementByXpath(currentVersionLink),
					"Clicking on the Version's CheckBox to verify");

		}

	// version1 verify
	@Test
		public void testActivityLogVersion1_10093(Method method) {
			homepage.launchAndLogin(method.getName());
			String riskToBeSelected = BaseUtils.testData.getTestData("RiskId");
			BaseUtils.navigateToARisk(riskToBeSelected);
			
			BaseUtils.getElements(BaseUtils.locators.getLocator("ActivityLog-version1"), "xpath");

			
		}

	/**
	 * After Method Clean Up-Capturing screenshot ,flushing the extent test and
	 * quitting the driver
	 * 
	 * @param result
	 */
	@AfterMethod
	public void cleanUp(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			BaseUtils.common.cleanUpOnSuccess();
		} else if (result.getStatus() == ITestResult.FAILURE) {
			BaseUtils.common.cleanUpOnFailure();
		}

	}

}
