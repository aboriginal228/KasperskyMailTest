package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;

import java.time.Duration;

public class AfterLoginPage extends Form {

    private IButton btnDownload;
    private IButton btnOs;
    private IButton btnSend2 = getElementFactory().getButton(By.xpath("//*[@data-at-selector='installerSendSelfBtn']"), "Send Button2");
    private IButton btnSend = getElementFactory().getButton(By.xpath("//*[@data-at-selector='sendToMe']"), "Send Button");
    private IButton btnOtherDownloads = getElementFactory().getButton(By.xpath("//*[@data-at-selector='otherDownloads']"), "Other Downloads Button");

    private ILabel iconCheck = getElementFactory().getLabel(By.xpath("//*[@icon='circle-check']"), "Icon");

    public AfterLoginPage(String os, String product) {
        super(By.xpath("//*[@class='w-carousel__slides__outer']"), "Slider");
        this.btnDownload = getElementFactory().getButton(By.xpath(String.format("//*[@data-at-selector='downloadApplicationCard' and contains(.,'%s')]//button[@data-at-selector='appInfoDownload']", product)), "Download Button");
        this.btnOs = getElementFactory().getButton(By.xpath(String.format("//*[@data-at-selector='osName' and contains(.,'%s')]", os)), "Os Select Button");
    }

    private void clkBtnDownload() {
        btnDownload.focus();
        btnDownload.click();
    }

    private void clkBtnOs() {
        btnOs.focus();
        btnOs.click();
    }

    private void clkBtnOther() {
        btnOtherDownloads.focus();
        btnOtherDownloads.click();
    }

    private void clkBtnSend() {
        btnSend.focus();
        btnSend.click();
    }

    private void checkIconExist() {
        iconCheck.state().waitForExist(Duration.ofSeconds(10));
    }

    private void clkBtnSend2() {
        btnSend2.focus();
        try {
            btnSend2.click();
        } catch (ElementClickInterceptedException e) {
            btnSend2.state().waitForClickable(Duration.ofSeconds(500));
            btnSend2.click();
        }
    }

    public void proceedToDownload() {
        clkBtnOs();
        clkBtnDownload();
        clkBtnOther();
        clkBtnSend();
        clkBtnSend2();
        checkIconExist();
    }

}
