package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertion extends SoftAssert {

    public static SoftAssertion softAssertion = new SoftAssertion();

    public static void softAssertAll() {
        try {
            softAssertion.assertAll("Custom Soft Assertion Failed");
        } catch (Exception e) {
            System.out.println("Custom Soft Assertion Failed");
        }
    }
}
