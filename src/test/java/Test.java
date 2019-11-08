import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Test {

    private static final String URL = "https://hpux.intranet:9445/JetB2_QA_3430X";
    private static final int SlEEP = 5000;

    private int COUNT_WINDOWS = 50;
    private ArrayList<String> tabs;


    @BeforeClass
    public static void setUp() {
        Configuration.browser = "Firefox";
        Configuration.startMaximized = true;
    }

    @org.junit.Test
    public void load() throws InterruptedException {

        for (int i = 1; i <= COUNT_WINDOWS; i++) {
            // Login
            open(URL);

            if (i == 1) {
                $(By.name("j_username")).setValue("creator");
                $(By.name("j_password_fake")).click();
                $(By.xpath("//*[@id=\"j_password::content\"]")).setValue("c67");
                $(By.id("cb1")).click();

                // session start
                $(By.name("it1")).sendKeys("777339");
                $(By.name("it1")).sendKeys(Keys.ENTER);
                $(By.xpath("//*[@id=\"regj_id_1:0:i1:0:cb2\"]")).click();
                Thread.sleep(SlEEP);
            }

            // deposits
            $(By.id("i1:2:cl8")).click();
            $(By.id("i2:44:i3:1:cl10")).click();
            $(By.id("i2:19:i3:3:cl10")).click();

            Thread.sleep(SlEEP);

            // Next Tab
            ((JavascriptExecutor) getWebDriver()).executeScript("window.open()");
            tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
            getWebDriver().switchTo().window(tabs.get(i));
        }
    }
    @After
    public void tearDown() {

    }
}
