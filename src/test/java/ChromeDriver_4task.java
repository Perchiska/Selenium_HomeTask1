import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.selenium.factory.WebDriverPool;

public class ChromeDriver_4task {
    WebDriver driver;
@Test
    public void startChrome(){

    //https://sites.google.com/a/chromium.org/chromedriver/capabilities
    ChromeOptions options = new ChromeOptions();

    //полный список опций находится по ссылке https://peter.sh/experiments/chromium-command-line-switches/
    options.addArguments("--disable-extensions");
    options.addArguments("disable-infobars");
    options.addArguments("start-maximized");
    options.addArguments("test-type");
    options.addArguments("--js-flags=--expose-gc");
    options.addArguments("--enable-precise-memory-info");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--disable-default-apps");
    options.addArguments("test-type=browser");
    options.addArguments("disable-infobars");

    System.out.println(driver = new ChromeDriver(options));
}

@Test
    public void startFireFox(){
  //http://kb.mozillazine.org/About:config_entries
    //http://kb.mozillazine.org/Category:Preferences
    FirefoxOptions options = new FirefoxOptions();
    options.addPreference("browser. display. focus_ring_width", 1);
    options.addArguments("-console");
    options.addPreference("browser. display. focus_text_color", true);

    driver= new FirefoxDriver(options);

}

@Test
    //https://seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_IE_InternetExplorerOptions.htm
    public void startIE(){
    InternetExplorerOptions caps = new InternetExplorerOptions();

    caps.ignoreZoomSettings()
        .setCapability("ignoreZoomSetting", true);

    driver = new InternetExplorerDriver(caps);
}


}

