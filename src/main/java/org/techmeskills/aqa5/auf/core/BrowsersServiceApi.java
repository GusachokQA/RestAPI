package org.techmeskills.aqa5.auf.core;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.techmeskills.aqa5.auf.utils.Waiters;

public class BrowsersServiceApi {
    private WebDriver driver = null;
    private DriverManagerType driverManagerType = null;
    private Waiters waiters;
    private String baseUrl;

    public BrowsersServiceApi() {
        ReadPropertiesApi readPropertiesApi = new ReadPropertiesApi();
        baseUrl = readPropertiesApi.getURL();

        switch (readPropertiesApi.getBrowserName().toLowerCase()) {
            case "chrome":
                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(readPropertiesApi.isHeadless());
                chromeOptions.addArguments("--disable-gpu");
                //chromeOptions.addArguments("--window-size=1920,1200");
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--silent");
                chromeOptions.addArguments("--start-maximized");

                driver = new ChromeDriver(chromeOptions);

                break;
            case "firefox":
                driverManagerType = DriverManagerType.FIREFOX;
                WebDriverManager.getInstance(driverManagerType).setup();

                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Browser " + readPropertiesApi.getBrowserName() + " is not supported.");
                break;
        }

        waiters = new Waiters(driver, readPropertiesApi.getTimeOut());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Waiters getWaiters() {
        return waiters;
    }

    public String getBaseUrl() { return baseUrl; }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
