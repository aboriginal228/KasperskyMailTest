package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {


    private final IButton btnSubmit = getElementFactory().getButton(By.xpath("//*[@type='submit']"), "Submit");

    private final ITextBox txbUsername = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'u-inputField__control') and @name='email']"), "Email Input");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'u-inputField__control') and @data-at-selector='passwordInput']"), "Password Input");
    private final IButton btnCookie = getElementFactory().getButton(By.id("CybotCookiebotDialogBodyLevelButtonAccept"), "Cookie Button");

    public LoginPage() {
        super(By.xpath("//*[contains(@class, 'entry-panel')]"), "Panel");
    }

    public void clickBtnCookie() {
        AqualityServices.getConditionalWait().waitFor(() -> btnCookie.state().waitForDisplayed());
        btnCookie.focus();
        btnCookie.click();
    }

    public void submit(String username, String password) {
        txbUsername.focus();
        txbUsername.type(username);
        txbPassword.focus();
        txbPassword.type(password);
        btnSubmit.focus();
        btnSubmit.clickAndWait();
    }


}
