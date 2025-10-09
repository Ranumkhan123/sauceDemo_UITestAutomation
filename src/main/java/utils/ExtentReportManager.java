package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {

            // Add timestamp
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            // Create reports folder if it doesn’t exist
            String reportDir = "test-output/reports/";
            new File(reportDir).mkdirs();  // create folder automatically if missing

            // Create a new timestamped report file
            String reportPath = reportDir + "ExtentReport_" + timeStamp + ".html";

            // Setup Extent Spark Reporter
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("SauceDemo UI Automation");
            spark.config().setDocumentTitle("Test Execution Report - " + timeStamp);

            // Attach to ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(spark);

            //  Add System Info
            extent.setSystemInfo("Tester", "Ranum Khan");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Generated On", timeStamp);
        }
        return extent;
    }
}
