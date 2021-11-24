import aquality.selenium.browser.AqualityServices;
import forms.AfterLoginPage;
import forms.DefaultAfterLoginPage;
import forms.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.ConfigUtil;
import utils.MailUtil;

import java.time.Duration;

public class KasperskyMailTest extends BaseTest{

    @Parameters({
            "product",
            "OS" })
    @Test
    void test1(
            String product,
            String OS) {


        AqualityServices.getLogger().info("Authorization");
        LoginPage loginPage = new LoginPage();
        loginPage.state().waitForDisplayed();
        loginPage.clickBtnCookie();
        loginPage.submit(ConfigUtil.getMailUsername(), ConfigUtil.getTestPassword());

        AqualityServices.getLogger().info("Moving to Downloads");
        DefaultAfterLoginPage defaultAfterLoginPage = new DefaultAfterLoginPage();
        defaultAfterLoginPage.state().waitForDisplayed();
        defaultAfterLoginPage.clkBtnDownloads();

        AqualityServices.getLogger().info("Checking product");
        AfterLoginPage afterLoginPage = new AfterLoginPage(OS, product);
        afterLoginPage.state().waitForDisplayed();
        afterLoginPage.proceedToDownload();

        Assert.assertTrue(
                AqualityServices.getConditionalWait().waitFor(
                        () -> MailUtil.checkMessage(product),
                        Duration.ofSeconds(10),
                        Duration.ofSeconds(2)),
                "Message is not received " +
                        "or received wrong message"
        );
    }
}
