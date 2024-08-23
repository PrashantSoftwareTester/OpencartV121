package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {

		logger.info("**** Starting TC001_AccountRegistrationTest **** ");
		try {

			HomePage hp=new HomePage(driver);

			hp.clickMyAccount();
			logger.info("**** Clikced on MyAccount Link");

			hp.clickRegister();
			logger.info("**** Clicked on Register Link");


			AccountRegistrationPage arp=new AccountRegistrationPage(driver);

			logger.info("Providing customer details");
			arp.setFirstName(randomestring().toUpperCase());
			arp.setLastName(randomestring().toUpperCase());
			arp.setEmail(randomestring()+"@gmail.com"); //randomly generated the email.
			arp.setTelephone(randomeNumber());

			String password=randomeAlphaNumeric();
			arp.setPassword(password);
			arp.setcnfPassword(password);
			arp.setPrivacyPolicy();
			arp.clickContinue();

			logger.info("*** Validating expected message ***");
			String confirmmsg=arp.getConfirmationMsg();

			if(confirmmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {

				logger.error("Test Failed...");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
            }

//	Assert.assertEquals(confirmmsg,"Your Account Has Been Created!");
			}

		catch(Exception e) {
          Assert.fail();
		}

		logger.info("**** Finished TC001_AccountRegistrationTest ****");

	}



}
