package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

	//Authenticated manager check positive case.
	public class AuthenticatedManagerCheckTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAllTasks(final int recordIndex, final String Username, final String Name,final String Surname,final String Email, final String Roles, final String Status) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a manager");
		super.clickOnSubmitButton("Accept");
		super.clickOnMenu("Administrator", "User accounts");
		
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("roleList", Roles);
		super.signOut();
	}
	
	//Listing negative test case.
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAllNegative(final int recordIndex, final String path) {
				super.navigateTo(path);
				super.checkErrorsExist();
			}
}