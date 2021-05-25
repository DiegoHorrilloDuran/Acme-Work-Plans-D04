package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class ManagerTaskDeleteTest extends AcmePlannerTest {
	
	//Delete positive test case.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
	public void createPositive(final int recordIndex, final String title, final String start, final String end, final String workload, final String description) {
		
		super.signIn("manager01", "manager01");
		
		super.clickOnMenu("Manager", "Manager tasks");
		
		super.clickOnListingRecord(recordIndex);
	
		super.clickOnSubmitButton("Delete"); 
		
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

}
