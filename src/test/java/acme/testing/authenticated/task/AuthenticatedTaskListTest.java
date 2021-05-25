package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/listAll.csv", encoding = "utf-8", numLinesToSkip = 1)
  @Order(10)
  public void listAllTasks(final int recordIndex, final String Title, final String Start,final String End,final String Workload, final String Description) {
		
		//Listing positive test case.
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "Public tasks");
		
		super.checkColumnHasValue(recordIndex, 0, Title);
		super.checkColumnHasValue(recordIndex, 1, Start);
		super.checkColumnHasValue(recordIndex, 2, End);
		
		
		
		//Show positive test case.
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", Title);
		super.checkInputBoxHasValue("start", Start);
		super.checkInputBoxHasValue("end", End);
		super.checkInputBoxHasValue("workload", Workload);
		super.checkInputBoxHasValue("description", Description);
		
		super.signOut();
	}
	
	//Listing negative test case.
			@ParameterizedTest
			@CsvFileSource(resources = "/authenticated/task/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(10)
			public void listAllNegative(final int recordIndex, final String path) {
							
				super.navigateTo(path);
				super.checkErrorsExist();
			}
}

