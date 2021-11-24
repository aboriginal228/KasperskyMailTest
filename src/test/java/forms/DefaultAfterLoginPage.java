package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class DefaultAfterLoginPage extends Form {

    private IButton btnDownloads = getElementFactory().getButton(By.xpath("//*[@data-tracking-action='Downloads | click']"), "Downloads Button");

    public DefaultAfterLoginPage() {
        super(By.xpath("//*[@data-tracking-action='Downloads | click']"), "Downloads Button");
    }

    public void clkBtnDownloads() {
        btnDownloads.focus();
        btnDownloads.click();
    }
}
