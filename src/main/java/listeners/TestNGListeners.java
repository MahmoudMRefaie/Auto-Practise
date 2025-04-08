package listeners;

import driver.DriverManager;
import org.framework.PropertiesUtils;
import org.testng.*;
import utils.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    @Override
    public void onExecutionStart() {
        ReportManager.info("TestNG is starting the execution");
        PropertiesUtils.loadProperties();
        FilesManager.deleteFile(new File("test-outputs/allure-results"));
        //FilesManager.cleanDirectory(new File("test-outputs/logs"));
        FilesManager.cleanDirectory(new File("test-outputs/screenshots"));
    }

    @Override
    public void onExecutionFinish() {
        ReportManager.info("TestNG has finished the execution");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {     //After method is finished

        if (method.isTestMethod()) {
            SoftAssertion.softAssertAll(testResult);

            switch (testResult.getStatus()) {
                case ITestResult.FAILURE:
                    ReportManager.error("Test method has failed", method.getTestMethod().getMethodName());
                    ScreenshotsUtils.takeScreenshot(DriverManager.getInstance(), "failed-" + testResult.getName() + "_" + TimestampUtils.getTimestamp());
                    break;
                case ITestResult.SKIP:
                    ReportManager.warn("Test method has been skipped:", method.getTestMethod().getMethodName());
                    ScreenshotsUtils.takeScreenshot(DriverManager.getInstance(), "skipped-" + testResult.getName() + "_" + TimestampUtils.getTimestamp());
                    break;
                case ITestResult.SUCCESS:
                    ReportManager.info("Test method has passed:", method.getTestMethod().getMethodName());
                    ScreenshotsUtils.takeScreenshot(DriverManager.getInstance(), "passed-" + testResult.getName() + "_" + TimestampUtils.getTimestamp());
                    break;
                default:
                    ReportManager.warn("Test method has an unexpected status:", method.getTestMethod().getMethodName());
                    break;
            }

            AllureUtils.attachLogsToAllureReport();
        }
        ReportManager.info("Test method has finished:", method.getTestMethod().getMethodName());
    }
}
