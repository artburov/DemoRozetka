package tests;

import com.codeborne.selenide.Configuration;
import com.google.common.collect.ImmutableMap;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static helpers.AttachmentHelper.*;

public class TestBase {
    static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        //Allure's environment settings
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "89.0.4389.90")
                        .put("URL", "http://aburov.local8808.net")
                        .build(), System.getProperty("user.dir")
                        + "/build/allure-results/");
        /**
         * Remote browser can be used in Jenkins for Selenoid UI
         * @param -Dremote.driver=https://%s:%s@selenoid.autotests.cloud/wd/hub/
         * @param -Dbrowser.select=firefox
         * @see Local and Selenoid run settings
         */
        Configuration.browser = System.getProperty("browser.select", "chrome");
        Configuration.browserSize = "1280x1024";
        String remoteWebDriver = System.getProperty("remote.driver");
        if (remoteWebDriver != null) {
            String user = driverConfig.remoteWebUser();
            String password = driverConfig.remoteWebPassword();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = String.format(remoteWebDriver, user, password);
        }
    }

    @AfterEach
    void customAttachments() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
        closeWebDriver();
    }
}