package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/listTask.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(42)
    public void listManagerTasks(final int recordIndex, final String title, final String start, 
    	final String end, final String workload, final String description, final String optionalLink, final String privacy) {
		//Listing positive test case.
		super.signIn("manager01","manager01");
		super.clickOnMenu("Manager", "Manager tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start);
		super.checkColumnHasValue(recordIndex, 2, end);
	
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("start", start);	
		super.checkInputBoxHasValue("end", end);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		
		super.signOut();
	}
	
	//Listing negative test case.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(43)
	public void listAllNegative(final int recordIndex, final String path) {
		super.signIn("manager01", "manager01");
		super.navigateTo(path);
		super.checkErrorsExist();
		super.signOut();
	}

}