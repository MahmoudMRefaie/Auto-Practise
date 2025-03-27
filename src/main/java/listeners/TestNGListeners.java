package listeners;

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
    public void afterInvocation(IInvokedMethod method, ITestResult testResult){     //After method is finished

        if (method.isTestMethod()) {
            switch (testResult.getStatus()) {
                case ITestResult.FAILURE:
                    ReportManager.error("Test method has failed", method.getTestMethod().getMethodName());
                    ScreenshotsUtils.takeScreenshot("failed-" + testResult.getName() + "_" + TimestampUtils.getTimestamp());
                    break;
                case ITestResult.SKIP:
                    ReportManager.warn("Test method has been skipped:", method.getTestMethod().getMethodName());
                    ScreenshotsUtils.takeScreenshot("skipped-" + testResult.getName() + "_" + TimestampUtils.getTimestamp());
                    break;
                case ITestResult.SUCCESS:
                    ReportManager.info("Test method has passed:", method.getTestMethod().getMethodName());
                    ScreenshotsUtils.takeScreenshot("passed-" + testResult.getName() + "_" + TimestampUtils.getTimestamp());
                    break;
                default:
                    ReportManager.warn("Test method has an unexpected status:", method.getTestMethod().getMethodName());
                    break;
            }

            try {
                SoftAssertion.softAssertAll();
            } catch (AssertionError e) {
                testResult.setStatus(ITestResult.FAILURE);
                testResult.setThrowable(e);
            }
            AllureUtils.attachLogsToAllureReport();
        }
        ReportManager.info("Test method has finished:", method.getTestMethod().getMethodName());
    }
}
