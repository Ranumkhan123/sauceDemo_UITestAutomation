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

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";
            File destFile = new File(destPath);
            Files.createDirectories(destFile.getParentFile().toPath());
            Files.copy(srcFile.toPath(), destFile.toPath());
            return destPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
