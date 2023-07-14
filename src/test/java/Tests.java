import org.junit.After;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Tests {
    String name;
    String surname;
    String address;
    String phoneNumber;
    String comment;
    String date;
    String finalMessage;

    public Tests(String name, String surname, String address, String phoneNumber, String comment, String date) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.comment = comment;


    }

    @Parameterized.Parameters
    public static Object[][] getPersonInformation() {
        return new Object[][]{//Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
                {"Котик", "Котиков", "Городской округ Котово", "79829999999", "21.01.2024", "я сам не могу, у меня лапки"},
                {"Собака", "Псовна", "Городской округ Чертаново", "79826667788", "21.06.2026", "вуф вуф"},

        };
    }



    WebDriver driver = new FirefoxDriver();
    @Test
    public void checkMainPage() {

        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainpage = new MainPage(driver);
        objMainpage.acceptCookie();
        assertTrue(objMainpage.checkAccordionPanel());

    }


    @Test
    public void checkOrderPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillPersonalInformation(name, surname, address, phoneNumber);
        objOrderPage.clickNextButton();
        objOrderPage.fillRentInformation(date, comment);
        assertThat(objOrderPage.resultMessage(), containsString("Номер заказа"));

    }

    //@After
    //public void teardown() {
    // Закрой браузер
    //  driver.quit();
    //}

}

