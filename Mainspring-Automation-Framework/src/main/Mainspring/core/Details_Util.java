package core;

import org.openqa.selenium.WebElement;

/**
 * 
 * @author Faiz-Siddiqh
 *
 */
public class Details_Util {

	/**
	 * To Update the Impact Area for Delivery Impact
	 * 
	 * @param ImpactScore1
	 * @param ImpactScore2
	 * @param cause
	 */
	public void updateImpactAreaForDeliveryImpact(String ImpactScore1, String ImpactScore2, String cause) {
		String impactScoreLocator = BaseUtils.locators.getLocator("Details-Impact-Score");
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "DeliveryImpactScore")),
				ImpactScore1);
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "LikelihoodScore")), ImpactScore2);
		BaseUtils.selectFromDropdown(BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "Causes")),
				cause);

	}

	/**
	 * To Update the Impact Area for Regulatory Impact
	 * 
	 * @param ImpactScore1
	 * @param ImpactScore2
	 * @param cause
	 */
	public void updateImpactAreaForRegulatoryImpact(String impactScore1, String impactScore2, String cause) {
		String impactScoreLocator = BaseUtils.locators.getLocator("Details-Impact-Score");
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "RegulatoryImpactScore")),
				impactScore1);
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "LikelihoodScore")), impactScore2);
		BaseUtils.selectFromDropdown(BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "Causes")),
				cause);

	}

	/**
	 * To Update the Impact Area for Reputational Impact
	 * 
	 * @param ImpactScore1
	 * @param ImpactScore2
	 * @param cause
	 */
	public void updateImpactAreaForReputationalImpact(String impactScore1, String impactScore2, String cause) {
		String impactScoreLocator = BaseUtils.locators.getLocator("Details-Impact-Score");
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "ReputationalImpactScore")),
				impactScore1);
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "LikelihoodScore")), impactScore2);
		BaseUtils.selectFromDropdown(BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "Causes")),
				cause);
	}

	/**
	 * To Update the Impact Area for TechnologyInfrastructure Impact
	 * 
	 * @param ImpactScore1
	 * @param ImpactScore2
	 * @param cause
	 */
	public void updateImpactAreaForTechnologyInfrastructureImpact(String impactScore1, String impactScore2,
			String cause) {
		String impactScoreLocator = BaseUtils.locators.getLocator("Details-Impact-Score");
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "TechInfraDataSecurityI")),
				impactScore1);
		BaseUtils.selectFromDropdown(
				BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "LikelihoodScore")), impactScore2);
		BaseUtils.selectFromDropdown(BaseUtils.getElementByXpath(impactScoreLocator.replaceAll("Score", "Causes")),
				cause);
	}

	/**
	 * To update the Target Closure Date
	 * 
	 * @param dateToBeSelected
	 */
	public void updateTargetClosureDate(String dateToBeSelected) {

		WebElement calendericon = BaseUtils
				.getElementByXpath(BaseUtils.locators.getLocator("Details-targetClosureDate"));
		BaseUtils.selectDateFromCalender(calendericon, dateToBeSelected);

	}

	/**
	 * @author Nazneen Shaik- To update the Mitigation Target Closure Date
	 * 
	 * @param dateToBeSelected
	 */
	public void updateMitigationTargetClosureDate(String dateToBeSelected) {

		WebElement calendericon = BaseUtils.getElementByXpath(
				BaseUtils.locators.getLocator("MitigationandContingency-Mitigationtargetclosuredate"));
		BaseUtils.selectDateFromCalender(calendericon, dateToBeSelected);

	}

	/**
	 * @author Nazneen Shaik-To update the Contingency Target Closure Date
	 * 
	 * @param dateToBeSelected
	 */
	public void updateContingencyTargetClosureDate(String dateToBeSelected) {

		WebElement calendericon = BaseUtils.getElementByXpath(
				BaseUtils.locators.getLocator("MitigationandContingency-Contingencytargetclosuredate"));
		BaseUtils.selectDateFromCalender(calendericon, dateToBeSelected);

	}

}
