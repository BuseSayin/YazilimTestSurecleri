package org.example;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class WebApp {

    private WebDriver driver;
    private WebDriverWait wait;

    public WebApp(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Beklenilen başlık belirlenir")
    public Object defineExpectedTitle() {
        return "ENUYGUN Türkiye'nin Seyahat Sitesi - Uçak Bileti, Otobüs, Otel";
    }

    @Step("Gerçek başlık gözlemlenir")
    public void isTitleActual(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Başlık doğru", defineExpectedTitle(), actualTitle);
    }

    @Step("Sağ üstteki Giriş yap butonuna basılır")
    public void pressedFirstLoginButton(){
        driver.findElement(By.cssSelector("[class=\"sc-EgOXT lhuWjM\"]")).click();
    }

    @Step("E-posta alanı doldurulur")
    public void fillEmailField(String email){
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys(email);
    }

    @Step("Şifre alanı doldurulur")
    public void fillPasswordField(String password) {
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys(password);
    }

    @Step("2. Giriş yap butonu olan ortadaki butona tıklanır")
    public void pressedSecondLoginButton() {
        driver.findElement(By.cssSelector("[class=\"sc-klVQfs dScLyI\"]")).click();
    }

    @Step("Sistemde kayıtlı olan kullanıcı girişi gözlemlenir")
    public void checkUserIsRegistered() {
        wait.until(ExpectedConditions.urlContains("https://www.enuygun.com/"));
        assertTrue("Kullanıcı sisteme başarılı bir şekilde giriş yaptı.", driver.getCurrentUrl().contains("https://www.enuygun.com/"));
    }

    @Step("Sistemde kayıtlı olmayan kullanıcı girişi gözlemlenir")
    public void checkUserIsNotRegistered() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"sc-eoVZPG kPvcPj sc-eoVZPG kPvcPj animation-end\"]")));
        assertTrue("Hata mesajı gösterildi.", driver.findElement(By.cssSelector("[class=\"sc-eoVZPG kPvcPj sc-eoVZPG kPvcPj animation-end\"]")).isDisplayed());
    }

    @Step("Üyeliğin yoksa/Kayıt ol butonuna basılır")
    public void pressedFirstRegisterButton() {
        driver.findElement(By.cssSelector("[class=\"sc-klVQfs ihrStG\"]")).click();
    }

    @Step("Kayıt ol butonuna basılır")
    public void pressedSecondRegisterButton() {
        driver.findElement(By.cssSelector("[class=\"sc-klVQfs dScLyI\"]")).click();
    }

    @Step("Sisteme giriş yapılması gözlemlenir")
    public void isUserLoggined(String message) {
        wait.until(ExpectedConditions.urlContains("https://www.enuygun.com/"));
        assertTrue(message, driver.getCurrentUrl().contains("https://www.enuygun.com/"));
    }
}
