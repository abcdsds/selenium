package ond.test.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class seleniumtest {


	private static WebDriver driver;

	//@Test
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver"); // �ٿ���� ChromeDriver ��ġ�� �־��ݴϴ�.
//		driver = new ChromeDriver(); // Driver ����
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //����ð� 5�ʼ���
//		driver.get("http://www.ondisk.co.kr"); // URL�� �����ϱ�
//		driver.findElement(By.xpath("//img[@alt='����ȸ������ 1000����Ʈ ����']")).click();
//
//		assertThat(driver.getTitle(),is("�µ�ũ"));
//		assertThat(driver.getCurrentUrl(),is("https://ondisk.co.kr/index.php?mode=join"));
//		
//		driver.close();
////		WebElement coolestWidgetEvah = driver.findElement(By.id("coolestWidgetEvah")); //id�� Element ��������
////		WebElement cheese = (WebElement) driver.findElements(By.className("cheese")); //Ŭ�����̸����� Element ��������
////		WebElement iframe = driver.findElement(By.tagName("iframe")); //�±��̸����� Element ��������
//
//	}
	
	public void proxyCheck() {
		
		
	}

	//@Test
	public void join() throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe"); // �ٿ���� ChromeDriver ��ġ�� �־��ݴϴ�.
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
		driver = new ChromeDriver(); // Driver ����
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //����ð� 5�ʼ���
		driver.get("http://ondisk.co.kr/index.php?mode=join"); // URL�� �����ϱ�
		
		System.out.println("1########################");
		
		//input[@id='id_str']
		
		driver.findElement(By.xpath("//input[@id='id_str']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='id_str']")).sendKeys("ansrl0099");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='pw_str1']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='pw_str1']")).sendKeys("123123");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='pw_str2']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='pw_str2']")).sendKeys("123123");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='nic_str']")).click();
		driver.findElement(By.xpath("//input[@id='nic_str']")).sendKeys("ansrl8716");
		
		driver.findElement(By.xpath("//input[@id='all_check']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='submitBtn']")).click();
		
		System.out.println("2########################");
		
//		System.out.println(driver.findElement(By.xpath("//img[@alt='����ȸ������ 1000����Ʈ ����']")));
//		assertThat(driver.getTitle(),is("�µ�ũ"));
//		assertThat(driver.getCurrentUrl(),is("http://ondisk.co.kr/index.php?mode=join"));
//		System.out.println("2########################");
		driver.close();
//		WebElement coolestWidgetEvah = driver.findElement(By.id("coolestWidgetEvah")); //id�� Element ��������
//		WebElement cheese = (WebElement) driver.findElements(By.className("cheese")); //Ŭ�����̸����� Element ��������
//		WebElement iframe = driver.findElement(By.tagName("iframe")); //�±��̸����� Element ��������

	}
	
	
	//@Test
	public void login() throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe"); // �ٿ���� ChromeDriver ��ġ�� �־��ݴϴ�.
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
		driver = new ChromeDriver(); // Driver ����
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //����ð� 5�ʼ���
		driver.get("http://ondisk.co.kr/index.php"); // URL�� �����ϱ�

		driver.getWindowHandles().forEach(v -> {
			if (driver.getWindowHandle().equals(v)) {
				driver.switchTo().window(v);
			}
		});
		
		assertThat(driver.getCurrentUrl(),is("http://ondisk.co.kr/index.php"));

		//driver.switchTo().window("�µ�ũ");
		//driver.switchTo().frame(driver.findElement(By.xpath("iframe[title='�µ�ũ']")));	
		//driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='http://ondisk.co.kr/index.php']")));

		
		driver.findElement(By.xpath("//input[@id='mb_id']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='mb_id']")).sendKeys("ansrl0099");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@name='mb_pw']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@name='mb_pw']")).sendKeys("123123");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@name='mb_pw']")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		
		driver.switchTo().alert().accept();
		
		Thread.sleep(500);
		
		driver.navigate().to("http://ondisk.co.kr/pop.php?sm=special_coupon");
		
		
		assertThat(driver.getCurrentUrl(),is("http://ondisk.co.kr/pop.php?sm=special_coupon"));
		
		String[] coupon = "L78W-NYFX-E8TYRG".split("-");
				
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='coupon_no1']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='coupon_no1']")).sendKeys(coupon[0]);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='coupon_no2']")).sendKeys(coupon[1]);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='coupon_no3']")).sendKeys(coupon[2]);
		Thread.sleep(500);
		driver.findElement(By.xpath("//img[contains(@src,'http://image.ondisk.co.kr/img/popup/special_coupon/btn.gif')]")).click();
		Thread.sleep(500);
		Alert alert = driver.switchTo().alert();
		if (alert.getText().indexOf("IP") != -1 ) {
			
		}
		
//		WebElement coolestWidgetEvah = driver.findElement(By.id("coolestWidgetEvah")); //id�� Element ��������
//		WebElement cheese = (WebElement) driver.findElements(By.className("cheese")); //Ŭ�����̸����� Element ��������
//		WebElement iframe = driver.findElement(By.tagName("iframe")); //�±��̸����� Element ��������

	}
}
