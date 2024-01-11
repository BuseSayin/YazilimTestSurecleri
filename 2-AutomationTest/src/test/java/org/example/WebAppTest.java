package org.example;

//Java-SDK 17 used for this version

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebAppTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebApp webAppPage;

    @BeforeClass
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.enuygun.com/");
        webAppPage = new WebApp(driver);  // WebApp nesnesini burada başlatın
    }

    @Test(description= "TC001 Anasayfa girişi")
    public void testHomepageLoad() {

        webAppPage.isTitleActual();

    }

    @Test(description= "TC002 Kayıtlı kullanıcı girişi")
    public void testRegisteredUserLogin() {

        driver.get("https://www.enuygun.com/");

        webAppPage.pressedFirstLoginButton();
        
        webAppPage.fillEmailField("buse@yopmail.com");
        
        webAppPage.fillPasswordField("123456789");

        webAppPage.pressedSecondLoginButton();

        // Beklenen başarılı giriş sonucu kayıtlı kullanıcı ile sisteme giriş yapma

        webAppPage.checkUserIsRegistered();

        driver.get("https://www.enuygun.com/");
    }


    @Test(description= "TC003 Kayıtlı olmayan kullanıcı girişi")
    public void testNonRegisteredUserLogin() {

        webAppPage.pressedFirstLoginButton();
        
        webAppPage.fillEmailField("busesayin9935@gmail.com");
        
        webAppPage.fillPasswordField("123456");
        
        webAppPage.pressedSecondLoginButton();

        // Sistemde kayıtlı olmayan kullanıcı hata mesajı

        webAppPage.checkUserIsNotRegistered();


    }

    @Test(description= "TC004 Kayıtlı olan kullanıcı üye olması")
    public void testRegisteredUserSignUp() {

        driver.get("https://www.enuygun.com/");

        webAppPage.pressedFirstLoginButton();

        webAppPage.pressedFirstRegisterButton();

        webAppPage.fillEmailField("buse@yopmail.com");

        webAppPage.fillPasswordField("123456789");

        webAppPage.pressedSecondRegisterButton();

        // Kayıtlı kullanıcının tekrar kayıt olmaya çalışması durumu ile sistemde hata mesajı alınması beklenirken
        // manuel test aşamalarında test edildiği için direkt sisteme giriş yaptığı gözlemlenmiş ve bu duruma göre
        // otomasyon edilmiştir

        webAppPage.isUserLoggined("Sistemde kayıtlı olan kullanıcı, sisteme başarılı bir şekilde giriş yaptı.");

    }

    @Test(description= "TC005 Kayıtlı olmayan kullanıcı üye olması")
    public void testNonRegisteredUserSignUp() {

        driver.get("https://www.enuygun.com/");

        webAppPage.pressedFirstLoginButton();

        webAppPage.pressedFirstRegisterButton();

        webAppPage.fillEmailField("techbuse@yopmail.com");

        webAppPage.fillPasswordField("tech123buse");

        webAppPage.pressedSecondRegisterButton();

        // Sistemde kayıtlı olmayan bir kullanıcı ile kayıt olunurken doğrulama kodu/linki doğrulanmadan sisteme giriş yapılması
        // beklenmezken manuel test aşamalarında sisteme direkt doğrulama olmadan giriş yapıldığı gözlemlenmiş ve otomasyon
        // bu duruma göre yapılmıştır

        webAppPage.isUserLoggined("Sistemde kayıtlı olmayan kullanıcı, sisteme başarılı bir şekilde kayıt oldu.");

    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}