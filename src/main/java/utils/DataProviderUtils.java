package utils;
import org.testng.annotations.DataProvider;
public class DataProviderUtils {
	@DataProvider(name = "leadData")
    public static Object[][] getLeadData() {
        return new Object[][] {
            {"John", "Doe", "OpenAI", "john.doe@example.com", "1234567890"},
            {"Jane", "Smith", "Google", "jane.smith@example.com", "9876543210"}
        };
    }
}
