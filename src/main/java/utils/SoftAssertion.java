package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class SoftAssertion extends SoftAssert {

    public static SoftAssertion softAssertion = new SoftAssertion();

    public static void softAssertAll(ITestResult result) {
        try {
            softAssertion.assertAll("Custom Soft Assertion Failed");
        } catch (AssertionError e) {
            System.out.println("Custom Soft Assertion Failed");
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        } finally {
            reinitializeSoftAssert();
        }
    }

    private static void reinitializeSoftAssert() {
        softAssertion = new SoftAssertion();
    }
}
