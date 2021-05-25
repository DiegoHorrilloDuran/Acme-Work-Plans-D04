package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {
	

	//Create Positive test case (all filled correctly and all filled except information).
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(13)
    public void createPositive(final int recordIndex, final String author, final String text, final String info) {
		
		super.clickOnMenu("Anonymous", "New shout");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.clickOnSubmitButton("Shout!");
		
	}
	

	/*Create Negative test case. Tries to update five invalid thresholds 
	 * (invalid name length, 
	 * information filled incorrectly, 
	 * text filled with spam, 
	 * text over 10% of spam and 
	 * all mistakes at the same time)*/
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(14)
	public void createNegative(final int recordIndex, final String author, final String text, final String info) {
			
		super.clickOnMenu("Anonymous", "New shout");
			
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
			
		super.clickOnSubmitButton("Shout!");
		super.checkErrorsExist();
			
	}
}
