package fbadremove;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ADRemoveTest {
	static WebDriver driver;

	@Test
	public void adRemoverTest() throws InterruptedException {
		String username, password;
		Scanner sc = new Scanner(System.in);
		username = sc.nextLine();
		password = sc.nextLine();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
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
		driver.quit();
	}

	public static void hideadd() {
		List<WebElement> hideElement = driver.findElements(By.xpath("//*[text()='Hide Ads']"));
		for (int i = 0; i < hideElement.size(); i++) {
			WebElement elementclick = hideElement.get(i);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementclick);
		}
		driver.navigate().refresh();
	}
}