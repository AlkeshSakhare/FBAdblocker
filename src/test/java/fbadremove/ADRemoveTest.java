package fbadremove;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ADRemoveTest {
	static WebDriver driver;
	static JavascriptExecutor executor;

	@Test(dataProvider = "getData")
	public void adRemoverTest(String username, String password) throws InterruptedException {

		try {
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			// browser setting to disable image
			prefs.put("profile.managed_default_content_settings.images", 2);
			// adding capabilities to browser
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			executor = ((JavascriptExecutor) driver);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String url = "http://facebook.com";
			driver.get(url);
			driver.findElement(By.id("email")).sendKeys(username);
			driver.findElement(By.id("pass")).sendKeys(password);
			driver.findElement(By.name("login")).click();
			Thread.sleep(14000);
			driver.navigate().to("https://www.facebook.com/adpreferences/advertisers");
			List<WebElement> element = driver.findElements(By.xpath("//*[text()='Hide Ads']"));
			if (element.size() > 0) {
				do {
					hideadd();
					element = driver.findElements(By.xpath("//*[text()='Hide Ads']"));
				} while (element.size() > 0);
			}
			driver.navigate().to("https://www.facebook.com/adpreferences/ad_settings");
			WebElement e1 = driver.findElement(By.xpath("//span[text()='Categories used to reach you']"));
			executor.executeScript("arguments[0].click();", e1);
			Thread.sleep(5000);
			WebElement interestFb = driver.findElement(By.xpath("//span[text()='Interest Categories']"));
			executor.executeScript("arguments[0].click();", interestFb);
			Thread.sleep(5000);
			WebElement seemore = driver.findElement(By.xpath("//span[text()='See All Interests']"));
			executor.executeScript("arguments[0].click();", seemore);
			List<WebElement> removeElement = driver.findElements(By.xpath("//span[text()='Remove']"));
			if (removeElement.size() > 0) {
				do {
					removeElement();
					removeElement = driver.findElements(By.xpath("//span[text()='Remove']"));
				} while (removeElement.size() > 0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	public static void hideadd() {
		List<WebElement> hideElement = driver.findElements(By.xpath("//*[text()='Hide Ads']"));
		for (int i = 0; i < hideElement.size(); i++) {
			WebElement elementclick = hideElement.get(i);
			executor.executeScript("arguments[0].click();", elementclick);
		}
		driver.navigate().refresh();
	}

	public static void removeElement() {
		List<WebElement> removeElement = driver.findElements(By.xpath("//span[text()='Remove']"));

		for (int i = 0; i < removeElement.size(); i++) {
			WebElement elementclick = removeElement.get(i);
			executor.executeScript("arguments[0].scrollIntoView(true);", elementclick);
			executor.executeScript("arguments[0].click();", elementclick);
		}
	}

	@DataProvider(name = "getData")
	public Object[][] getData() {

		Object[][] obj = { { "test@yahoo.com", "your_pass" }, { "test@gmail.com", "your_pass" }

		};
		return obj;
	}

}