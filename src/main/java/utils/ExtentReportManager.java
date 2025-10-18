package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            // Timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Safe Windows path
            Path reportFile = Paths.get(System.getProperty("user.dir"), "test-output", "reports",
                    "ExtentReport_" + timestamp + ".html");
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile.toString());

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }


}
