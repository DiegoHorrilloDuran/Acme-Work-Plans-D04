package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class ManagerTaskCreateTest extends AcmePlannerTest {
	
	//Create positive test case.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
	public void createPositive(final int recordIndex, final String title, final String start, final String end, final String workload, final String description) {
		
		super.signIn("manager01", "manager01");
		
		
		super.clickOnMenu("Manager", "Create task");
				
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("start", start);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		
		super.clickOnSubmitButton("Create"); 
		
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
		
		
		super.signOut();
		
	}
	

	/*Create Negative test case. Tries to update eight invalid thresholds 
	 * (all the fields are empty, 
	 * spam word in title, 
	 * spam word in description, 
	 * start and end dates are past,
	 * end date is before start date,
	 * workload is more than execution period,
	 * workload is 0 and 
	 * workload is negative)*/
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
	public void createNegative(final int recordIndex, final String title, final String start, final String end, final String workload, final String description) {
		
		super.signIn("manager01", "manager01");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("start", start);
		super.fillInputBoxIn("end", end);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		
		super.clickOnSubmitButton("Create"); 
		
		super.checkErrorsExist(); 
		
		
		super.signOut();
		
	}

}
