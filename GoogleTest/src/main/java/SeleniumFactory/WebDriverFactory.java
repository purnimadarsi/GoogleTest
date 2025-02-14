package SeleniumFactory;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.function.Supplier;


import java.util.Map;

public class WebDriverFactory {
	
	private static final Map<String,Supplier<WebDriver>> DRIVER_MAP = new HashMap<>();
	
	static {
		ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-debugging-port=9222");
    	options.addArguments("--remote-allow-origins=*");


		DRIVER_MAP.put("chrome", ()->new ChromeDriver(options));
		DRIVER_MAP.put("firefox",()-> new FirefoxDriver());
		DRIVER_MAP.put("edge",()-> new EdgeDriver());

		
	}
   public static WebDriver getDriver(String browser) {
	   Supplier<WebDriver> driverSupplier = DRIVER_MAP.get(browser.toLowerCase());
	   if(driverSupplier != null) {
		   return driverSupplier.get();
	   }
	   throw new IllegalArgumentException("Invalid browser name"+ browser);
   }
}



























