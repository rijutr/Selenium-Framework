package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < MAX_RETRY) {
            count += 1;
            return true;
        }
        return false;
    }
}
