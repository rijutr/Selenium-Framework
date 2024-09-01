package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {
    public static ExtentReports getReports(){
        String path = System.getProperty("user.dir") + "/src/test/Reports/index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Automation Test Report");
        extentSparkReporter.config().setDocumentTitle("Test Results");
        /*
        The above snippet is to set the Metadata to ExtentReports
         */

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(extentSparkReporter);
        reports.setSystemInfo("BROWSER","FireFox");
        return reports;
    }
}
