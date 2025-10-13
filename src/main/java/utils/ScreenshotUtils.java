package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

        public static String captureScreenshot(WebDriver driver, String testName) {
            try {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotDir = "test-output/screenshots/";
                new File(screenshotDir).mkdirs(); // Create folder if not exist

                String filePath = screenshotDir + testName + "_" + timestamp + ".png";

                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(srcFile.toPath(), new File(filePath).toPath());

                return filePath;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
}


