package christian.php.myadmin.sql;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sqlexecute {
	
@Test
public void execute() throws InterruptedException, AWTException {

    ChromeOptions options = new ChromeOptions();



WebDriverManager.chromedriver().driverVersion("107.0.5304.6200").setup();
WebDriver driver=new ChromeDriver(options);


	driver.navigate().to("http://localhost/phpmyadmin/index.php?route=/table/sql&db=upwork1&table=arvindstudenttable");
	String query="INSERT INTO `arvindstudenttable`(`stu1`, `stu2`, `stu3`, `stu4`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]')";
	driver.findElement(By.cssSelector("#clear")).click();
	Thread.sleep(2000);
//	 driver.findElement(By.cssSelector("#insert")).click();

	
	WebElement queryElement = driver.findElement(By.xpath("(//pre[@role='presentation'])[3]"));
	JavascriptExecutor j = (JavascriptExecutor) driver;
    j.executeScript("arguments[0].click();", queryElement);
	Thread.sleep(2000);
	
	//Create instance of Robot class
	   Robot robot = new Robot();
	//Create instance of Clipboard class

	   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	//Set the String to Enter

	  StringSelection stringSelection = new StringSelection(query);
	//Copy the String to Clipboard

	  clipboard.setContents(stringSelection, null);
	//Use Robot class instance to simulate CTRL+C and CTRL+V key events :

	  robot.keyPress(KeyEvent.VK_CONTROL);
	  robot.keyPress(KeyEvent.VK_V);
	  robot.keyRelease(KeyEvent.VK_V);
	  robot.keyRelease(KeyEvent.VK_CONTROL);
	  
	  Thread.sleep(3000);
	  
		 WebElement Go =  driver.findElement(By.xpath("(//input[@value='Go'])[2]"));
		 JavascriptExecutor j2 = (JavascriptExecutor) driver;
		    j2.executeScript("arguments[0].click();", Go);
			Thread.sleep(2000);
	String status = driver.findElement(By.cssSelector(".alert-success")).getText();

	Assert.assertTrue(status.contains("1 row inserted"));

}


}
