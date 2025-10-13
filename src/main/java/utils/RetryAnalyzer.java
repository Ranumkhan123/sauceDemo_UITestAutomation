package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private final int maxRetry = 1; // maximum 3 tries

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++;
            System.out.println("Retrying test " + result.getMethod().getMethodName() + " - Attempt " + count);
            return true; // retry test
        }
        return false; // stop retrying, mark fail
    }
}
